package org.jcat.plugin;

import org.jcat.context.Context;

public interface IReplacePlugin extends IPlugin {
    public String replace(Context context, String src);
}
