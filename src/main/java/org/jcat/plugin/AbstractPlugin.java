package org.jcat.plugin;

import org.jcat.cmd.CatOption;

public abstract class AbstractPlugin implements IPlugin {

    protected CatOption option;

    public AbstractPlugin() {
    }

    public void setOption(CatOption option) {
        this.option = option;
    }
}
