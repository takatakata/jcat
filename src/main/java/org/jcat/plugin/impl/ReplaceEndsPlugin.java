package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceEndsPlugin extends AbstractReplacePlugin {

    public ReplaceEndsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(Context context, String src) {
        return src + "$";
    }

	@Override
	public boolean isEnabled() {
		return option.isShowEnds();
	}
}
