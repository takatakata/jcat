package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

/**
 * FIXME: 制約事項：999,999行を超える場合行番号が「%6d」に収まらない
 */
public class ReplaceNumberPlugin extends AbstractReplacePlugin {

    public ReplaceNumberPlugin() {
    	super();
    }

    @Override
    public String replaceLine(Context context, String src) {
		context.incrementLineNum();
		String margin = (src.length() > 0) ? "\t" : "\t";
		return String.format("%6d%s", context.getLineNum(), margin) + src;
    }
    
	@Override
	public boolean isEnabled() {
		return option.isNumber();
	}
}
