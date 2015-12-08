package apukhtin.courses.task1.analizer;

import java.io.File;
import java.util.Set;

import com.beust.jcommander.Parameter;

public class ArgsAnalizer {
	@Parameter(names = {"-i", "--input"}, converter = FileConverter.class, required = true)
	File input;
	
	@Parameter(names = {"-t", "--task"}, required = true)
	Set<Task> tasks;
	
	@Parameter(names = "--help")
	boolean showHelp;
}
