package org.jcat.context;

public class FileContext {
    private CommandContext context;

    private String linePrevious = null;
    private String lineCurrent = null;

    public FileContext() {
    }

    public FileContext(CommandContext context) {
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

    public long getLineNum() {
        return context.getLineNum();
    }

    public long incrementLineNum() {
        return context.incrementLineNum();
    }
}
