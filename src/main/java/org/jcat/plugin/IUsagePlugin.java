package org.jcat.plugin;

import java.io.IOException;

import org.jcat.output.IOutput;

public interface IUsagePlugin extends IPlugin {
    public void usage(IOutput output) throws IOException;
}
