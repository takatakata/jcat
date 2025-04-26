package org.jcat.plugin;

import org.jcat.core.stream.StreamContext;

public abstract class AbstractReplacePlugin extends AbstractPlugin implements IReplacePlugin {

    public AbstractReplacePlugin() {
        super();
    }

    @Override
    public final String replace(StreamContext context, String src) {
        if (!isEnabled() || src == null) {
            return src;
        }
        return replaceLine(context, src);
    }

    protected abstract String replaceLine(StreamContext context, String src);
}
