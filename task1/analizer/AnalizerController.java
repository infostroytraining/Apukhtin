package apukhtin.courses.task1.analizer;
import java.io.File;
import java.io.IOException;

import apukhtin.courses.task1.analizer.command.Command;

public class AnalizerController {
	private static File file;
	private static String text;
	private final static String HELP_TEXT = "HELP:\nUse only utf8 decoded files\n"
			+ "--Here must be help.--";
	
	public static void process(ArgsAnalizer analizer) throws IOException {
		if(analizer == null) throw new IllegalArgumentException("Analizer arg is null");
		if(analizer.isShowHelp()) {
			System.out.println(HELP_TEXT);
			return;
		}
		
		file = analizer.getInput();
		text = AnalizerUtils.loadTextFromFile(file).toLowerCase();
		
		Command task = analizer.getTask();
		task.execute(text);
	}
	
}
