package org.jcat.plugin.impl;

import org.jcat.plugin.AbstractUsagePlugin;

public class UsageHelpPlugin extends AbstractUsagePlugin {

	private static final String HELP_MESSAGE = "Usage: java -cp /path/to/classes org.jcat.JCatApp [OPTION]... [FILE]...\n"
			+
			"Concatenate FILE(s) to standard output.\n" +
			"\n" +
			"With no FILE, or when FILE is -, read standard input.\n" +
			"\n" +
			"  -A, --show-all           equivalent to -vET (**Not yet supported)\n" +
			"  -b, --number-nonblank    number nonempty output lines, overrides -n\n" +
			"  -e                       equivalent to -vE (**Not yet supported)\n" +
			"  -E, --show-ends          display $ at end of each line\n" +
			"  -n, --number             number all output lines\n" +
			"  -s, --squeeze-blank      suppress repeated empty output lines\n" +
			"  -t                       equivalent to -vT (**Not yet supported)\n" +
			"  -T, --show-tabs          display TAB characters as ^I\n" +
			"  -u                       (ignored)\n" +
			"  -v, --show-nonprinting   use ^ and M- notation, except for LFD and TAB (**Not yet supported)\n" +
			"      --help     display this help and exit\n" +
			"      --version  output version information and exit\n" +
			"\n" +
			"Examples:\n" +
			"  java -cp /path/to/classes org.jcat.JCatApp f - g  Output f's contents, then standard input, then g's contents.\n"
			+
			"  java -cp /path/to/classes org.jcat.JCatApp        Copy standard input to standard output.";

	public UsageHelpPlugin() {
		super();
	}

	@Override
	public void show() {
		System.out.println(HELP_MESSAGE);
	}

	@Override
	public boolean isEnabled() {
		return option.isShowHelp();
	}
}
