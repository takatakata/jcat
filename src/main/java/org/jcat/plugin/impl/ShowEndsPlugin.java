package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractPlugin;

public class ShowEndsPlugin extends AbstractPlugin {

    public ShowEndsPlugin() {
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
        return src + "$";
    }
}
