package org.jcat.plugin;

import org.jcat.core.CatOption;

public interface IPlugin {
    public void setOption(CatOption option);

    public boolean isEnabled();
}
