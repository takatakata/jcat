package org.jcat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jcat.cmd.CatOption;
import org.jcat.context.CommandContext;
import org.jcat.context.FileContext;
import org.jcat.input.FileInput;
import org.jcat.input.IInput;
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

    public JCat(String[] args) throws FileNotFoundException {

        this.option = new CatOption(args);
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

    public void exec() throws IOException {
        if (option.isUsageEnabled()) usage();
        if (option.isCatEnabled()) cat();
    }

    private void usage() throws IOException {
        pluginHolder.usage();
    }

    private void cat() throws IOException {
        try (IOutput output = new StandardOutput("\n")) {
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
    }

    public static void main(String[] args) {
        try {
            JCat app = new JCat(args);
            app.exec();
            System.exit(EXIT_SUCCESS);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(EXIT_FAILURE);
        }
    }
}
