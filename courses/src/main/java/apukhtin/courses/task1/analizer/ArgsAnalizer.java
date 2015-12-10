package apukhtin.courses.task1.analizer;

import java.io.File;

import com.beust.jcommander.Parameter;

import apukhtin.courses.task1.analizer.command.Command;

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
