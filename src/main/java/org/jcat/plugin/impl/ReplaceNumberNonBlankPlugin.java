package org.jcat.plugin.impl;

import org.jcat.context.Context;
import org.jcat.plugin.AbstractReplacePlugin;

/**
 * FIXME: 制約事項：999,999行を超える場合行番号が「%6d」に収まらない
 */
public class ReplaceNumberNonBlankPlugin extends AbstractReplacePlugin {

	public ReplaceNumberNonBlankPlugin() {
		super();
	}

	@Override
	public String replaceLine(Context context, String src) {
		if ("".equals(src)) {
			//現在行が空行のときは何もしない
			return src;
		}
        String margin = (src != null && src.length() > 0) ? "\t" : "";
		context.incrementLineNum();
		return String.format("%6d%s", context.getLineNum(), margin) + src;
	}

	@Override
	public boolean isEnabled() {
		return option.isNumberNonBlank();
	}
}
