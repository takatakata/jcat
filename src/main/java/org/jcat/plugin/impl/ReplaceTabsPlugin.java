package org.jcat.plugin.impl;

import java.util.regex.Pattern;

import org.jcat.context.FileContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceTabsPlugin extends AbstractReplacePlugin {

    private static final Pattern PATTERN_TABS = Pattern.compile("\t");

    public ReplaceTabsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(FileContext context, String src) {
        return PATTERN_TABS.matcher(src).replaceAll("^I");
    }
    
	@Override
	public boolean isEnabled() {
		return option.isShowTabs();
	}
}
