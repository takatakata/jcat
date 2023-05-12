package org.jcat.plugin;

import org.jcat.core.stream.StreamContext;

public interface IPlugin {
    public String replace(StreamContext context, String src);
}
