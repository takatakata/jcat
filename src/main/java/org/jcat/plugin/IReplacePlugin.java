package org.jcat.plugin;

import org.jcat.context.FileContext;

public interface IReplacePlugin extends IPlugin {
    public String replace(FileContext context, String src);
}
