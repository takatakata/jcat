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
    	if (context.getLineNum() == 0) {
			//最初の1行目の場合は行番号を出力する
			;
		} else if (!context.isLinePreviouLastCharLF()) {
			//前回改行が含まれなかったときはまだ行の途中であるため行番号を付加しない
			return src;
		}
		context.incrementLineNum();
		return String.format("%6d\t", context.getLineNum()) + src;
    }
    
	@Override
	public boolean isEnabled() {
		return option.isNumber();
	}
}
