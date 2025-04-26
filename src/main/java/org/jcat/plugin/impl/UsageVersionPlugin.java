package org.jcat.plugin.impl;

import org.jcat.plugin.AbstractUsagePlugin;

public class UsageVersionPlugin extends AbstractUsagePlugin {

	private static final String VERSION = "jcat 1.0-SNAPSHOT\n" +
			"Copyright (C) 2023 takatakata.\n" +
			"License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>.\n" +
			"This is free software: you are free to change and redistribute it.\n" +
			"There is NO WARRANTY, to the extent permitted by law.\n" +
			"            \n" +
			"Written by takatakata.";

	public UsageVersionPlugin() {
		super();
	}
		
	@Override
	public void show() {
		System.out.println(VERSION);
	}
	
	@Override
	public boolean isEnabled() {
		return option.isShowVersion();
	}
}
