package org.jcat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

import org.jcat.cmd.CatOption;
import org.jcat.context.Context;
import org.jcat.input.FileInput;
import org.jcat.input.IInput;
import org.jcat.output.FileOutput;
import org.jcat.output.IOutput;
import org.jcat.output.StandardOutput;
import org.jcat.plugin.IReplacePlugin;
import org.jcat.plugin.IUsagePlugin;
import org.jcat.plugin.PluginHolder;
import org.jcat.plugin.impl.ReplaceEndsPlugin;
import org.jcat.plugin.impl.ReplaceNumberNonBlankPlugin;
import org.jcat.plugin.impl.ReplaceNumberPlugin;
import org.jcat.plugin.impl.ReplaceShowNonPrintingPlugin;
import org.jcat.plugin.impl.ReplaceSqueezeBlankPlugin;
import org.jcat.plugin.impl.ReplaceTabsPlugin;
import org.jcat.plugin.impl.UsageHelpPlugin;
import org.jcat.plugin.impl.UsageVersionPlugin;

/**
 * JCat
 */
public class JCat {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = 1;

    private CatOption options;
    private PluginHolder plugins;
    private OutputStream externalOutput;

    public JCat(String[] commands) {

        this.options = new CatOption(commands);
        this.plugins = new PluginHolder(options);

        this.plugins.addUsagePlugin(new IUsagePlugin[] {
                new UsageHelpPlugin(),
                new UsageVersionPlugin()
        });

        this.plugins.addReplacePlugin(new IReplacePlugin[] {
                new ReplaceEndsPlugin(),
                new ReplaceNumberNonBlankPlugin(),
                new ReplaceNumberPlugin(),
                new ReplaceSqueezeBlankPlugin(),
                new ReplaceTabsPlugin(),
                new ReplaceShowNonPrintingPlugin()
        });
    }

    public JCat(String[] options, OutputStream externalOutput) {
        this(options);
        this.externalOutput = externalOutput;
    }
    

    public void exec() throws IOException {
        IOutput output = null;
        try {
            if (externalOutput == null) {
                output = new StandardOutput("\n");
            } else {
                output = new FileOutput(externalOutput, "\n");
            }
            if (options.isUsageEnabled()) {
                usage(output);
            }
            if (options.isCatEnabled()) {
                cat(output);
            }
        } finally {
            output.flush();
            if (externalOutput == null) {
                output.close();
            }
        }
    }

    private void usage(IOutput output) throws IOException {
        plugins.usage(output);
    }

    private void cat(IOutput output) throws IOException {
        AtomicLong lineNum = new AtomicLong(0L);
        for (String path : options.getFileList()) {
            Context context = new Context(lineNum);
            String line;
            try (IInput input = new FileInput(path)) {
                while ((line = input.read()) != null) {
                    context.rotate(line);
                    line = plugins.replace(context, line);
                    if (line != null) {
                        output.writeLine(line);
                    }  
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
