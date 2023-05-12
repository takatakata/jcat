package org.jcat.core.stream;

import org.jcat.core.CatOption;

public class StreamContext {
    private CatOption option;
    private long lineNumInput = 0;
    private long lineNumOutput = 1;
    private String lineCurrent = null;
    private String lineNext = null;
    private boolean seekFinished = false;

    public StreamContext() {
    }

    public StreamContext(CatOption option) {
        this.option = option;
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
        return lineNumOutput;
    }
    public void setLineNumOutput(long lineNumOutput) {
        this.lineNumOutput = lineNumOutput;
    }
    public long incrementLineNumOutput() {
        return ++lineNumOutput;
    }
}
