package org.jcat.context;

import java.util.concurrent.atomic.AtomicLong;

public class Context {

    private String linePrevious = null;
    private String lineCurrent = null;
    private AtomicLong lineNum;

    public Context() {
    }

    public Context(AtomicLong lineNum) {
        this();
        this.lineNum = lineNum;
    }

    public void rotate(String line) {
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
        return lineNum.longValue();
    }

    public long incrementLineNum() {
        return lineNum.incrementAndGet();
    }
}
