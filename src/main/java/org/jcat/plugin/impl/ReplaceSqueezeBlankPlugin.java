package org.jcat.plugin.impl;

import org.jcat.context.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceSqueezeBlankPlugin extends AbstractReplacePlugin {

    public ReplaceSqueezeBlankPlugin() {
    	super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
    	if("".equals(context.getLinePrevious()) && "".equals(context.getLineCurrent())) {
            return null;
        }
        return src;
    }
    
	@Override
	public boolean isEnabled() {
		return option.isSqueezeBlank();
	}
}
