package apukhtin.courses.task1.analizer;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AnalizerController {
	private static File file;
	private static String text;
	
	public static void process(ArgsAnalizer analizer) throws IOException {
		file = analizer.input;
		text = AnalizerUtils.loadTextFromFile(file).toLowerCase();
		
		Set<Task> tasks = analizer.tasks;
		tasks.forEach(task -> execTask(task));
	}
	
	private static void execTask(Task t) {
		if(t.equals(Task.FREQUENCY) || t.equals(Task.LENGTH)) {
			ElaplseTimeCounter.start();
			
			Map<String, Integer> res = null;
			
			if(t.equals(Task.FREQUENCY)) {
				System.out.println("Frequency task:");
				res = TextAnalizer.frequency(text);
			} else {
				System.out.println("Length task: ");
				res = TextAnalizer.length(text);
			}
			for(Entry<String, Integer> entry : res.entrySet()) {
				System.out.println(entry.getKey() + " -> " + entry.getValue());
			}
			
			ElaplseTimeCounter.end();
		} if(t.equals(Task.DUPLICATES)) {
			ElaplseTimeCounter.start();
			System.out.println("Duplicates task:");
			List<String> dublicates = TextAnalizer.duplicates(text);
			
			dublicates.forEach( el -> System.out.println(el));
			
			ElaplseTimeCounter.end();
		}
	}
}
