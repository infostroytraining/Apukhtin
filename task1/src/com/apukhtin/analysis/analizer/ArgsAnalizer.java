package com.apukhtin.analysis.analizer;

import com.apukhtin.analysis.analizer.converter.CommandConverter;
import com.apukhtin.analysis.analizer.converter.FileConverter;
import com.apukhtin.analysis.command.Command;
import com.beust.jcommander.Parameter;

import java.io.File;

public class ArgsAnalizer {
	@Parameter(names = {"-i", "--input"}, converter = FileConverter.class)
	private File input;
	
	@Parameter(names = {"-t", "--task"}, converter = CommandConverter.class)
	private Command task;
	
	@Parameter(names = "--help")
	private boolean showHelp;

	@Parameter(names = "--parallel")
	private boolean inParallel;

	public boolean isInParallel() {
		return inParallel;
	}

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
