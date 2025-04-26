package org.jcat.plugin;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jcat.core.CatOption;
import org.jcat.core.stream.StreamContext;

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
    protected Set<IHelpPlugin> helpPlugins = new LinkedHashSet<>();

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

    public void addHelpPlugin(IHelpPlugin plugins[]) {
        Arrays.asList(plugins).forEach(plugin -> {
            plugin.setOption(option);
            if (plugin.isEnabled()) {
                helpPlugins.add(plugin);
            }
        });
    }

    public void showUsages() {
        for (IHelpPlugin plugin : helpPlugins) {
            plugin.show();
        }
    }

    public String replace(StreamContext context, String line) {
        for (IReplacePlugin plugin : replacePlugins) {
            line = plugin.replace(context, line);
        }
        return line;
    }

    public boolean existsUsages() {
        return helpPlugins.size() > 0;
    }
}
