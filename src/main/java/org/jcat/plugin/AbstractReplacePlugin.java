package org.jcat.plugin;

import org.jcat.context.Context;

public abstract class AbstractReplacePlugin extends AbstractPlugin implements IReplacePlugin {

    public AbstractReplacePlugin() {
        super();
    }

    @Override
    public final String replace(Context context, String src) {
        if (src == null) {
            return src;
        }
        return replaceLine(context, src);
    }

    protected abstract String replaceLine(Context context, String src);
}
