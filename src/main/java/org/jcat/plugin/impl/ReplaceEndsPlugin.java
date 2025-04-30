package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceEndsPlugin extends AbstractReplacePlugin {

    public ReplaceEndsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(Context context, String src) {
    	if (!context.isCurrentHasLF()) {
    		//改行が含まれないときはまだ行の途中であるため行末記号を付加しない
    		return src;
    	}
        return src + "$";
    }

	@Override
	public boolean isEnabled() {
		return option.isShowEnds();
	}
}
