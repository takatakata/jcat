package org.jcat.context;

public class CommandContext {

    private long lineNum = 0;

    public long getLineNum() {
        return lineNum;
    }

    public long incrementLineNum() {
        return ++lineNum;
    }
}
