package org.jcat.core;

import java.util.ArrayList;
import java.util.List;

public class CatOption {
    private boolean showAll = false;
    private boolean numberNonBlank = false;
    private boolean showEnds = false;
    private boolean number = false;
    private boolean squeezeBlank = false;
    private boolean equivalentToVT = false;
    private boolean equivalentToVE = false;
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
                    break;
                case "--show-all":
                    showAll = true;
                    break;
                case "-b":
                    numberNonBlank = true;
                    break;
                case "--number-nonblank":
                    numberNonBlank = true;
                    break;
                case "-e":
                    equivalentToVE = true;
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
                    equivalentToVT = true;
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
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public void setNumberNonBlank(boolean numberNonBlank) {
        this.numberNonBlank = numberNonBlank;
    }

    public void setShowEnds(boolean showEnds) {
        this.showEnds = showEnds;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }

    public void setSqueezeBlank(boolean squeezeBlank) {
        this.squeezeBlank = squeezeBlank;
    }

    public void setEquivalentToVE(boolean equivalentToVE) {
        this.equivalentToVE = equivalentToVE;
    }

    public void setEquivalentToVT(boolean equivalentToVT) {
        this.equivalentToVT = equivalentToVT;
    }

    public void setShowTabs(boolean showTabs) {
        this.showTabs = showTabs;
    }

    public void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }

    public void setShowNonPrinting(boolean showNonPrinting) {
        this.showNonPrinting = showNonPrinting;
    }

    public void setShowHelp(boolean showHelp) {
        this.showHelp = showHelp;
    }

    public void setShowVersion(boolean showVersion) {
        this.showVersion = showVersion;
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

    public boolean isEquivalentToVE() {
        return equivalentToVE;
    }

    public boolean isEquivalentToVT() {
        return equivalentToVT;
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

    public List<String> getFileList() {
        return fileList;
    }
}
