package apukhtin.courses.task1.analizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AnalizerUtils {
	public static String loadTextFromFile(File f) throws IOException {
		String result = "";
		
		List<String> strings = Files.lines(f.toPath()).collect(Collectors.toList());
		
		for(String s : strings) {
			result += s;
		}
		
		return result;
	}
}
