package org.jcat.plugin;

import java.io.IOException;

import org.jcat.output.IOutput;

public abstract class AbstractUsagePlugin extends AbstractPlugin implements IUsagePlugin {

    public AbstractUsagePlugin() {
        super();
    }

    @Override
    public void usage(IOutput output) throws IOException {
        if (isEnabled()) {
            show(output);
        }
    }

    protected abstract void show(IOutput output) throws IOException;
}
