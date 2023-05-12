package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractPlugin;

public class SqueezeBlankPlugin extends AbstractPlugin {

    public SqueezeBlankPlugin(boolean enabled) {
        super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
        if("".equals(context.getLineCurrent()) && "".equals(context.getLineNext())) {
            return null;
        }
        return src;
    }
}
