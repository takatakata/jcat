package org.jcat.plugin;

public abstract class AbstractUsagePlugin extends AbstractPlugin implements IUsagePlugin {

    public AbstractUsagePlugin() {
        super();
    }

    @Override
    public final void usage() {
        if (isEnabled()) {
            show();
        }
    }

    protected abstract void show();
}
