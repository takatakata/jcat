package org.jcat.context;

public class GlobalContext {

    private long lineNumOutput = 0;

    public long getLineNumOutput() {
        return lineNumOutput;
    }

    public void setLineNumOutput(long lineNumOutput) {
        this.lineNumOutput = lineNumOutput;
    }

    public long incrementLineNumOutput() {
        return ++lineNumOutput;
    }
}
