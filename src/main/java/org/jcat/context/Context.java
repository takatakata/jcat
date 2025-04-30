package org.jcat.context;

public class Context {

	private Long lineNum = 0L;
	private String lineCurrent = null;
	private String linePrevious = null;
	private boolean linePreviousLastCharLF = false;
	private boolean lineCurrentLastCharLF = true;

	public Context() {
		linePrevious = null;
		lineCurrent = null;
		linePreviousLastCharLF = true;
		lineCurrentLastCharLF = false;
	}

	public void rotateLine(String line) {
		if (lineCurrent == null) {
			lineCurrent = line;
		} else {
			linePrevious = lineCurrent;
			lineCurrent = line;
		}
		linePreviousLastCharLF = lineCurrentLastCharLF;
		lineCurrentLastCharLF = (lineCurrent.length() > 0 && lineCurrent.charAt(lineCurrent.length() - 1) == '\n')
				? true
				: false;
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
		return lineNum = lineNum + 1;
	}

	public boolean isLinePreviouLastCharLF() {
		return linePreviousLastCharLF;
	}

	public boolean isLineCurrentLastCharLF() {
		return lineCurrentLastCharLF;
	}
}
