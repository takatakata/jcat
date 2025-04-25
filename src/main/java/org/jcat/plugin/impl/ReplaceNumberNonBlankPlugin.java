package org.jcat.plugin.impl;

import org.jcat.core.stream.StreamContext;
import org.jcat.plugin.AbstractReplacePlugin;

/**
 * 制約事項：999,999行を超える場合行番号が「%6d」に収まらない
 */
public class ReplaceNumberNonBlankPlugin extends AbstractReplacePlugin {

	public ReplaceNumberNonBlankPlugin() {
		super();
	}

	@Override
	public String replaceLine(StreamContext context, String src) {
		if (!isEnabled() || "".equals(context.getLineCurrent())) {
			//現在行が空行のときは何もしない
			return src;
		}
		context.incrementLineNumOutput();
		return String.format("%6d  ", context.getLineNumOutput()) + src;
	}

	@Override
	public boolean isEnabled() {
		return option.isNumberNonBlank();
	}
}
