package org.jcat.plugin;

import org.jcat.cmd.CatOption;

public interface IPlugin {
    public void setOption(CatOption option);

    public boolean isEnabled();
}
