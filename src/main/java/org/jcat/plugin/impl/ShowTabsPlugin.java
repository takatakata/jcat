package org.jcat.plugin.impl;

import java.util.regex.Pattern;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractPlugin;

public class ShowTabsPlugin extends AbstractPlugin {

    private static final Pattern PATTERN_TABS = Pattern.compile("\t");

    public ShowTabsPlugin() {
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
        return PATTERN_TABS.matcher(src).replaceAll("^I");
    }
}
