package apukhtin.courses.task1;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import apukhtin.courses.task1.analizer.TextAnalizer;



public class Main {

	public static void main(String[] args) throws Exception {
//		ArgsAnalizer analizer = new ArgsAnalizer();
//		String[] argv = {"-i", "C:\\Users\\Vlad\\Desktop\\1.txt", "-t", "length"};
//		new JCommander(analizer, argv);
//		start();
//		StringTokenizer stringTokenizer = new StringTokenizer("Привет. Меня, ёпта, зовут владик!!!", 
//				"!?., :;\t\n\f");
//		
//		while(stringTokenizer.hasMoreTokens()) {
//			System.out.println(stringTokenizer.nextToken());
//		}
//		
//		end();
//		
//		HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {
//			{
//				put("a", 3);
//			}
//		};
//		
//		hashMap.replace("a", hashMap.get("a") + 1);
//		
//		System.out.println(hashMap.get("a"));
		
		Map<String, Integer> m = TextAnalizer.length("Привет. Я Я ёлка, вот.");
		
		for(Entry<String, Integer> entry : m.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
	}
}
