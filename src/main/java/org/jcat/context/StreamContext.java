package org.jcat.context;

public class StreamContext {
    private GlobalContext context;

    private String linePrevious = null;
    private String lineCurrent = null;

    public StreamContext() {
    }

    public StreamContext(GlobalContext context) {
        this();
        this.context = context;
    }

    public void addLine(String line) {
        if (lineCurrent == null) {
            lineCurrent = line;
        } else {
            linePrevious = lineCurrent;
            lineCurrent = line;
        }
    }

    public String getLinePrevious() {
        return linePrevious;
    }

    public String getLineCurrent() {
        return lineCurrent;
    }

    public long getLineNumOutput() {
        return context.getLineNumOutput();
    }

    public long incrementLineNumOutput() {
        return context.incrementLineNumOutput();
    }
}
