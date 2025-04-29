package org.jcat;

import java.io.IOException;
import java.io.OutputStream;

import org.jcat.cmd.CatOption;
import org.jcat.context.CommandContext;
import org.jcat.context.FileContext;
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

    private CatOption option;
    private PluginHolder pluginHolder;
    private OutputStream os;

    public JCat(String[] options) {

        this.option = new CatOption(options);
        this.pluginHolder = new PluginHolder(option);

        this.pluginHolder.addUsagePlugin(new IUsagePlugin[] {
                new UsageHelpPlugin(),
                new UsageVersionPlugin()
        });

        this.pluginHolder.addReplacePlugin(new IReplacePlugin[] {
                new ReplaceEndsPlugin(),
                new ReplaceNumberNonBlankPlugin(),
                new ReplaceNumberPlugin(),
                new ReplaceSqueezeBlankPlugin(),
                new ReplaceTabsPlugin(),
                new ReplaceShowNonPrintingPlugin()
        });
    }

    public JCat(String[] options, OutputStream os) {
        this(options);
        this.os = os;
    }
    

    public void exec() throws IOException {
        IOutput output = null;
        try {
            output = (os == null) ? new StandardOutput("\n") : new FileOutput(os, "\n");
            if (option.isUsageEnabled()) {
                usage(output);
            }
            if (option.isCatEnabled()) {
                cat(output);
            }
        } finally {
            if (os == null) {
                output.close();
            }
        }
    }

    private void usage(IOutput output) throws IOException {
        pluginHolder.usage(output);
    }

    private void cat(IOutput output) throws IOException {
        CommandContext commandContext = new CommandContext();
        for (String path : option.getFileList()) {
            try (IInput input = new FileInput(path)) {
                FileContext fileContext = new FileContext(commandContext);
                String line;
                while ((line = input.read()) != null) {
                    fileContext.addLine(line);
                    line = pluginHolder.replace(fileContext, line);
                    if (line == null) {
                        continue;
                    }
                    output.writeLine(line);
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
