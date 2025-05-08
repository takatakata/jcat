package org.jcat.plugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jcat.cmd.CatOption;
import org.jcat.context.Context;
import org.jcat.output.IOutput;

/**
 * プラグイン管理用クラス。
 */
public class PluginManager {

    protected CatOption option;
    protected Set<IReplacePlugin> replacePlugins = new LinkedHashSet<>();
    protected Set<IUsagePlugin> usagePlugins = new LinkedHashSet<>();

    public PluginManager() {
    }

    public PluginManager(CatOption option) {
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

    public void usage(IOutput output) throws IOException {
        for (IUsagePlugin plugin : usagePlugins) {
            plugin.usage(output);
        }
    }

    public String replace(Context context, String line) {
    	//改行を含まない部分を対象にReplacePluginによる置き換えを行う
    	String contents = line.substring(0, context.isCurrentHasLF() ? line.length() - 1 : line.length());
        for (IReplacePlugin plugin : replacePlugins) {
        	contents = plugin.replace(context, contents);
        }
		//nullの場合は出力しない（連続空白行など）
    	if (contents == null) {
    		return null;
    	}
    	//行末の改行文字を復元して結果を返す
        return contents + (context.isCurrentHasLF() ? "\n" : "");
    }
}
