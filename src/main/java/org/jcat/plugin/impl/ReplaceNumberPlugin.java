package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

/**
 * 制約事項：999,999行を超える場合行番号が「%6d」に収まらない
 */
public class ReplaceNumberPlugin extends AbstractReplacePlugin {

    public ReplaceNumberPlugin() {
    	super();
    }

    @Override
    public String replaceLine(StreamContext context, String src) {
		context.incrementLineNumOutput();
		return String.format("%6d  ", context.getLineNumOutput()) + src;
    }
    
	@Override
	public boolean isEnabled() {
		return option.isNumber();
	}
}
