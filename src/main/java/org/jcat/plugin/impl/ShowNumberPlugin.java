package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractPlugin;

/**
 * 制約事項：999,999行を超える場合行番号が「%6d」に収まらない
 */
public class ShowNumberPlugin extends AbstractPlugin {
 
    public ShowNumberPlugin() {
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
        return String.format("%6d  ", context.getLineNumOutput()) + src;
    }
}
