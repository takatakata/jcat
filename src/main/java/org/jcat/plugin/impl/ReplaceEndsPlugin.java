package org.jcat.plugin.impl;

import org.jcat.context.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceEndsPlugin extends AbstractReplacePlugin {

    public ReplaceEndsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
        return src + "$";
    }

	@Override
	public boolean isEnabled() {
		return option.isShowEnds();
	}
}
