package org.jcat.plugin;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jcat.core.CatOption;
import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.impl.ShowEndsPlugin;
import org.jcat.plugin.impl.ShowNumberNonBlankPlugin;
import org.jcat.plugin.impl.ShowNumberPlugin;
import org.jcat.plugin.impl.ShowTabsPlugin;
import org.jcat.plugin.impl.SqueezeBlankPlugin;

/**
 * 下記オプション未対応。
 *  「-A」「--show-all」
 *  「-e」（VEオプションに同じ）
 *  「-v」「--show-nonprinting」
 *  「-t」（VTオプションに同じ）
 *  「」
 */
public class PluginHolder {

    private Set<IPlugin> plugins = new LinkedHashSet<>();

    public PluginHolder() {
    }

    public PluginHolder(CatOption option) {
        this();
        registPlugins(option);
    }

    protected void registPlugins(CatOption option) {
        if (option.isSqueezeBlank()) {
            addPlugin(new SqueezeBlankPlugin(true));
        }
        if (option.isShowTabs()) {
            addPlugin(new ShowTabsPlugin());
        }
        if (option.isNumber()) {
            addPlugin(new ShowNumberPlugin());
        }
        if (option.isNumberNonBlank()) {
            addPlugin(new ShowNumberNonBlankPlugin());
        }
        if (option.isShowEnds()) {
            addPlugin(new ShowEndsPlugin());
        }
    }

    protected void addPlugin(IPlugin plugin) {
        plugins.add(plugin);
    }

    public String processPlugins(StreamContext context, String line) {
        for (IPlugin plugin : plugins) {
            line = plugin.replace(context, line);
        }
        return line;
    }
}
