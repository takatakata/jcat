package org.jcat.context;

import org.jcat.cmd.CatOption;

public class StreamContext {
    private CatOption option;
    private GlobalContext context;

    private String linePrevious = null;
    private String lineCurrent = null;

    public StreamContext() {
    }

    public StreamContext(GlobalContext context, CatOption option) {
        this.option = option;
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

    public CatOption getOption() {
        return option;
    }

    public void setOption(CatOption option) {
        this.option = option;
    }
    
    public String getLinePrevious() {
        return linePrevious;
    }

    public void setLinePrevious(String linePrevious) {
        this.linePrevious = linePrevious;
    }
    public String getLineCurrent() {
        return lineCurrent;
    }

    public void setLineCurrent(String lineCurrent) {
        this.lineCurrent = lineCurrent;
    }

    public long getLineNumOutput() {
        return context.getLineNumOutput();
    }

    public void setLineNumOutput(long lineNumOutput) {
        context.setLineNumOutput(lineNumOutput);
    }

    public long incrementLineNumOutput() {
        return context.incrementLineNumOutput();
    }
}
