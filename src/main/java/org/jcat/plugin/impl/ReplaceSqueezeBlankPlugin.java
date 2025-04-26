package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceSqueezeBlankPlugin extends AbstractReplacePlugin {

    public ReplaceSqueezeBlankPlugin() {
    	super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
    	if("".equals(context.getLineCurrent()) && "".equals(context.getLineNext())) {
            return null;
        }
        return src;
    }
    
	@Override
	public boolean isEnabled() {
		return option.isSqueezeBlank();
	}
}
