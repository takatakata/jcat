package org.jcat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jcat.core.CatOption;
import org.jcat.core.input.FileInput;
import org.jcat.core.input.IInput;
import org.jcat.core.output.IOutput;
import org.jcat.core.output.StandardOutput;
import org.jcat.core.stream.IJCatStream;
import org.jcat.core.stream.JCatStream;
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
 * JCatApp
 */
public class JCat {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = 1;

    private CatOption option;
    private IOutput output;
    private PluginHolder pluginHolder;

    public JCat(String[] args) throws FileNotFoundException {

        this.option = new CatOption(args);
        this.output = new StandardOutput("\r\n");
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

    public void usage() throws IOException {
        if (pluginHolder.isUsageEnabled()) {
            pluginHolder.usage();
        }
    }

    public void cat() throws IOException {
        if (pluginHolder.isUsageEnabled()) {
            return;
        }
        for (String path : option.getFileList()) {
            IInput input = new FileInput(path);
            try (IJCatStream<?, ?> stream = new JCatStream<IInput, IOutput>(
                    this.option, input, this.output)) {
                while (stream.next()) {
                    String line = stream.readLine();
                    line = pluginHolder.replace(stream.getContext(), line);
                    if (line == null) {
                        continue;
                    }
                    stream.writeLine(line);
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        try {
            JCat cat = new JCat(args);
            cat.usage();
            cat.cat();
            System.exit(EXIT_SUCCESS);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(EXIT_FAILURE);
        }
    }
}
