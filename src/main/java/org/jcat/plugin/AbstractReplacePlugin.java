package org.jcat.plugin;

import org.jcat.context.FileContext;

public abstract class AbstractReplacePlugin extends AbstractPlugin implements IReplacePlugin {

    public AbstractReplacePlugin() {
        super();
    }

    @Override
    public final String replace(FileContext context, String src) {
        if (src == null) {
            return src;
        }
        return replaceLine(context, src);
    }

    protected abstract String replaceLine(FileContext context, String src);
}
