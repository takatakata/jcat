package org.jcat.plugin;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jcat.cmd.CatOption;
import org.jcat.context.FileContext;

/**
 * 下記オプション未対応。
 *  「-A」「--show-all」
 *  「-e」（VEオプションに同じ）
 *  「-v」「--show-nonprinting」
 *  「-t」（VTオプションに同じ）
 *  「」
 */
public class PluginHolder {

    protected CatOption option;
    protected Set<IReplacePlugin> replacePlugins = new LinkedHashSet<>();
    protected Set<IUsagePlugin> usagePlugins = new LinkedHashSet<>();

    public PluginHolder() {
    }

    public PluginHolder(CatOption option) {
        this();
        this.option = option;
    }

    public void addReplacePlugin(IReplacePlugin plugins[]) {
        Arrays.asList(plugins).forEach(plugin -> {
            plugin.setOption(option);
            if (plugin.isEnabled()) {
                replacePlugins.add(plugin);
            }
        });
    }

    public void addUsagePlugin(IUsagePlugin plugins[]) {
        Arrays.asList(plugins).forEach(plugin -> {
            plugin.setOption(option);
            if (plugin.isEnabled()) {
                usagePlugins.add(plugin);
            }
        });
    }

    public void usage() {
        for (IUsagePlugin plugin : usagePlugins) {
            plugin.usage();
        }
    }

    public String replace(FileContext context, String line) {
        for (IReplacePlugin plugin : replacePlugins) {
            line = plugin.replace(context, line);
        }
        return line;
    }
}
