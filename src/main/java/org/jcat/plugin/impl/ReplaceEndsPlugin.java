package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceEndsPlugin extends AbstractReplacePlugin {

    public ReplaceEndsPlugin() {
    	super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
    	if (isEnabled()) {
            return src + "$";
    	} else {
    		return src;
    	}
    }

	@Override
	public boolean isEnabled() {
		return option.isShowEnds();
	}
}
