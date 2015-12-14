package com.apukhtin.analysis.analizer;

import java.io.File;

import com.apukhtin.analysis.analizer.converter.CommandConverter;
import com.apukhtin.analysis.analizer.converter.FileConverter;
import com.beust.jcommander.Parameter;

import com.apukhtin.analysis.command.Command;

public class ArgsAnalizer {
	@Parameter(names = {"-i", "--input"}, converter = FileConverter.class)
	private File input;
	
	@Parameter(names = {"-t", "--task"}, converter = CommandConverter.class)
	private Command task;
	
	@Parameter(names = "--help")
	private boolean showHelp;

	public File getInput() {
		return input;
	}

	public Command getTask() {
		return task;
	}

	public boolean isShowHelp() {
		return showHelp;
	}
	
}
