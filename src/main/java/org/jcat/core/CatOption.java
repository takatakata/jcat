package org.jcat.core;

import java.util.ArrayList;
import java.util.List;

public class CatOption {
    private boolean showAll = false;
    private boolean numberNonBlank = false;
    private boolean showEnds = false;
    private boolean number = false;
    private boolean squeezeBlank = false;
    private boolean showTabs = false;
    private boolean ignored = false;
    private boolean showNonPrinting = false;
    private boolean showHelp = false;
    private boolean showVersion = false;
    private List<String> fileList = new ArrayList<>();

    public CatOption() {
    }

    public CatOption(String[] args) {
        // System.err.println("Parameters::");
        for (String arg : args) {
            // System.err.println(" - [" + arg + "]");
            switch (arg) {
            case "-A":
                showAll = true;
                showEnds = true;
                showTabs = true;
                showNonPrinting = true;
                break;
            case "--show-all":
                showAll = true;
                showEnds = true;
                showTabs = true;
                showNonPrinting = true;
                break;
            case "-b":
                numberNonBlank = true;
                break;
            case "--number-nonblank":
                numberNonBlank = true;
                break;
            case "-e":
                showNonPrinting = true;
                showEnds = true;
                break;
            case "-E":
                showEnds = true;
                break;
            case "--show-ends":
                showEnds = true;
                break;
            case "-n":
                number = true;
                break;
            case "--number":
                number = true;
                break;
            case "-s":
                squeezeBlank = true;
                break;
            case "--squeeze-blank":
                squeezeBlank = true;
                break;
            case "-T":
                showTabs = true;
                break;
            case "--show-tabs":
                showTabs = true;
                break;
            case "-v":
                showNonPrinting = true;
                break;
            case "--show-nonprinting":
                showNonPrinting = true;
                break;
            case "-t":
                showNonPrinting = true;
                showTabs = true;
                break;
            case "-u":
                ignored = true;
                break;
            case "--help":
                showHelp = true;
                break;
            case "--version":
                showVersion = true;
                break;
            default:
                addFileList(arg);
                break;
            }
        }
        if( isShowHelp() && isShowVersion() ) {
            if (String.join(",", args).matches(".*--help.*--version.*")) {
                showVersion = false;
            } else {
                showHelp = false;
            }
        }
    }

    public boolean isUsageEnabled() {
        return isShowHelp() || isShowVersion();
    }

    public List<String> getFileList() {
        return fileList;
    }
    
    public void addFileList(String path) {
        this.fileList.add(path);
    }

    public boolean isShowAll() {
        return showAll;
    }

    public boolean isNumberNonBlank() {
        return numberNonBlank;
    }

    public boolean isShowEnds() {
        return showEnds;
    }

    public boolean isNumber() {
        return number;
    }

    public boolean isSqueezeBlank() {
        return squeezeBlank;
    }

    public boolean isShowTabs() {
        return showTabs;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public boolean isShowNonPrinting() {
        return showNonPrinting;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowVersion() {
        return showVersion;
    }

}
