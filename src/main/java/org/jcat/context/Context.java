package org.jcat.context;

public class Context {

    private Long lineNum = 0L;
    private String current = null;
    private String previous = null;
    private boolean previousHasLF = false;
    private boolean currentHasLF = true;

    public Context() {
        previous = null;
        current = null;
        previousHasLF = true;
        currentHasLF = false;
    }

    public void rotate(String line) {
        if (current == null) {
            current = line;
        } else {
            previous = current;
            current = line;
        }
        previousHasLF = currentHasLF;
        currentHasLF = (current.length() > 0 && current.charAt(current.length() - 1) == '\n') ? true : false;
    }

    public String getPrevious() {
        return previous;
    }

    public String getCurrent() {
        return current;
    }

    public long getLineNum() {
        return lineNum.longValue();
    }

    public long incrementLineNum() {
        return lineNum = lineNum + 1;
    }

    public boolean isPreviousHasLF() {
        return previousHasLF;
    }

    public boolean isCurrentHasLF() {
        return currentHasLF;
    }
}
