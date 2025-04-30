package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceSqueezeBlankPlugin extends AbstractReplacePlugin {

    public ReplaceSqueezeBlankPlugin() {
        super();
    }

    @Override
    public String replaceLine(Context context, String src) {
        if ("\n".equals(context.getLinePrevious()) && "\n".equals(context.getLineCurrent())) {
            return null;
        }
        return src;
    }

    @Override
    public boolean isEnabled() {
        return option.isSqueezeBlank();
    }
}
