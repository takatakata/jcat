package org.jcat.plugin;

import org.jcat.core.stream.StreamContext;

public interface IReplacePlugin extends IPlugin {
    public String replace(StreamContext context, String src);
}
