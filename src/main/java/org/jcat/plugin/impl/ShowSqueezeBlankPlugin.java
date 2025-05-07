package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

public class ShowSqueezeBlankPlugin extends AbstractReplacePlugin {

    public ShowSqueezeBlankPlugin() {
        super();
    }

    @Override
    public String replaceLine(Context context, String src) {
        if ("\n".equals(context.getPrevious()) && "\n".equals(context.getCurrent())) {
            return null;
        }
        return src;
    }

    @Override
    public boolean isEnabled() {
        return option.isShowSqueezeBlank();
    }
}
