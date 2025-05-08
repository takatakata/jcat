package org.jcat;

import java.io.IOException;
import java.io.OutputStream;

import org.jcat.cmd.CatOption;
import org.jcat.context.Context;
import org.jcat.input.FileInput;
import org.jcat.input.IInput;
import org.jcat.output.FileOutput;
import org.jcat.output.IOutput;
import org.jcat.plugin.IReplacePlugin;
import org.jcat.plugin.IUsagePlugin;
import org.jcat.plugin.PluginManager;
import org.jcat.plugin.impl.ShowEndsPlugin;
import org.jcat.plugin.impl.ShowNumberNonBlankPlugin;
import org.jcat.plugin.impl.ShowNumberPlugin;
import org.jcat.plugin.impl.ShowNonPrintingPlugin;
import org.jcat.plugin.impl.ShowSqueezeBlankPlugin;
import org.jcat.plugin.impl.ShowTabsPlugin;
import org.jcat.plugin.impl.UsageHelpPlugin;
import org.jcat.plugin.impl.UsageVersionPlugin;

/**
 * JCat
 */
public class JCat {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = 1;

    private CatOption options;
    private PluginManager pluginManager;
    private OutputStream externalOutput;

    public JCat(String[] commands) {

        this.options = new CatOption(commands);
        this.pluginManager = new PluginManager(options);

        this.pluginManager.addUsagePlugin(new IUsagePlugin[] {
                new UsageHelpPlugin(),
                new UsageVersionPlugin()
        });

        this.pluginManager.addReplacePlugin(new IReplacePlugin[] {
                new ShowEndsPlugin(),
                new ShowNumberNonBlankPlugin(),
                new ShowNumberPlugin(),
                new ShowSqueezeBlankPlugin(),
                new ShowTabsPlugin(),
                new ShowNonPrintingPlugin()
        });
    }

    public JCat(String[] options, OutputStream externalOutput) {
        this(options);
        this.externalOutput = externalOutput;
    }

    public void exec() throws IOException {
        IOutput output = null;
        try {
            output = new FileOutput(externalOutput, "\n");
            usage(output);
            cat(output);
        } finally {
            output.flush();
            if (externalOutput == null) {
                output.close();
            }
        }
    }

    private void usage(IOutput output) throws IOException {
        if (!options.isUsageEnabled()) {
            return;
        }
        pluginManager.usage(output);
    }

    private void cat(IOutput output) throws IOException {
        if (!options.isCatEnabled()) {
            return;
        }
        Context context = new Context();
        for (String path : options.getFileList()) {
            String line;
            try (IInput input = new FileInput(path)) {
                while ((line = input.read()) != null) {
                    context.rotate(line);
                    line = pluginManager.replace(context, line);
                    if (line == null) {
                        //nullの場合は出力しない（連続空白行など）
                        continue;
                    }
                    output.write(line);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            JCat app = new JCat(args);
            app.exec();
            System.exit(EXIT_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(EXIT_FAILURE);
        }
    }
}
