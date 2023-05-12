package org.jcat.plugin;

import org.jcat.core.stream.StreamContext;

public abstract class AbstractPlugin implements IPlugin {

    public AbstractPlugin() {
    }

    @Override
    public final String replace(StreamContext context, String src) {
        if (src == null) {
            return src;
        }
        return replaceLine(context, src);
    }

    protected abstract String replaceLine(StreamContext context, String src);
}
