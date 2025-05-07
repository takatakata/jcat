package org.jcat.plugin.impl;

import java.util.regex.Pattern;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

public class ShowTabsPlugin extends AbstractReplacePlugin {

    private static final Pattern PATTERN_TABS = Pattern.compile("\t");

    public ShowTabsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(Context context, String src) {
        return PATTERN_TABS.matcher(src).replaceAll("^I");
    }
    
	@Override
	public boolean isEnabled() {
		return option.isShowTabs();
	}
}
