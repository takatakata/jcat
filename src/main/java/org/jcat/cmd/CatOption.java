package org.jcat.cmd;

import java.util.ArrayList;
import java.util.List;

public class CatOption {
    private boolean showAll = false;
    private boolean showNumberNonBlank = false;
    private boolean showEnds = false;
    private boolean showNumber = false;
    private boolean showSqueezeBlank = false;
    private boolean showTabs = false;
    private boolean showNonPrinting = false;
    private boolean usageHelp = false;
    private boolean usageVersion = false;
    private boolean ignored = false;
    private List<String> fileList = new ArrayList<>();

    public CatOption() {
    }

    public CatOption(String[] options) {
        for (String option : options) {
            if (option.startsWith("--")) {
                //オプション形式「--show-all」
                addOption(option);
            } else if (option.matches("^-[A-Za-z]+$")) {
                //オプション形式「-A」「-vT」
                option.substring(1).chars().forEach(ch -> {
                    addOption(new StringBuilder("-").appendCodePoint(ch).toString());
                });
            } else if (option.matches("^[^-].*")) {
                //オプション形式ファイルパス
                addFileList(option);
            }
        }
        if( isUsageHelp() && isUsageVersion() ) {
            if (String.join(",", options).matches(".*--help.*--version.*")) {
                usageVersion = false;
            } else {
                usageHelp = false;
            }
        }
    }
    
    private void addOption(String option) {
        switch (option) {
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
            showNumberNonBlank = true;
            break;
        case "--number-nonblank":
            showNumberNonBlank = true;
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
            showNumber = true;
            break;
        case "--number":
            showNumber = true;
            break;
        case "-s":
            showSqueezeBlank = true;
            break;
        case "--squeeze-blank":
            showSqueezeBlank = true;
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
            usageHelp = true;
            break;
        case "--version":
            usageVersion = true;
            break;
        default:
            addFileList(option);
            break;
        }
    }

    public boolean isUsageEnabled() {
        return isUsageHelp() || isUsageVersion();
    }

    public boolean isCatEnabled() {
        return !isUsageEnabled();
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

    public boolean isShowNumberNonBlank() {
        return showNumberNonBlank;
    }

    public boolean isShowEnds() {
        return showEnds;
    }

    public boolean isShowNumber() {
        return showNumber;
    }

    public boolean isShowSqueezeBlank() {
        return showSqueezeBlank;
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

    public boolean isUsageHelp() {
        return usageHelp;
    }

    public boolean isUsageVersion() {
        return usageVersion;
    }

}
