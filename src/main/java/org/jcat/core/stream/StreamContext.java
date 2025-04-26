package org.jcat.core.stream;

import org.jcat.core.CatOption;

public class StreamContext {
    private CatOption option;
    private GlobalContext context;

    private long lineNumInput = 0;
    private String lineCurrent = null;
    private String lineNext = null;
    private boolean seekFinished = false;

    public StreamContext() {
    }

    public StreamContext(GlobalContext context, CatOption option) {
        this.option = option;
        this.context = context;
    }

    public CatOption getOption() {
        return option;
    }

    public void setOption(CatOption option) {
        this.option = option;
    }

    public String getLineCurrent() {
        return lineCurrent;
    }

    public void setLineCurrent(String lineCurrent) {
        this.lineCurrent = lineCurrent;
    }

    public String getLineNext() {
        return lineNext;
    }

    public void setLineNext(String lineNext) {
        this.lineNext = lineNext;
    }

    public boolean isSeekFinished() {
        return seekFinished;
    }

    public void setSeekFinished(boolean seekFinished) {
        this.seekFinished = seekFinished;
    }

    public long getLineNumInput() {
        return lineNumInput;
    }

    public void setLineNumInput(long lineNum) {
        this.lineNumInput = lineNum;
    }

    public long incrementLineNumInput() {
        return ++lineNumInput;
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
