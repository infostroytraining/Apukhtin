package apukhtin.courses.task1.analizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TextAnalizer {

	public static Map<String, Integer> frequency(String  text) {
		Map<String, Integer> result = new HashMap<>();
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			
			if(result.containsKey(token)) { 
				result.replace(token, result.get(token) + 1);
			} else {
				result.put(token, 1);
			}
		}
				
		return result;
	}

	public static Map<String, Integer> length(String text) {
		Map<String, Integer> words = new TreeMap<>((s1, s2) -> s2.compareTo(s1));
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");

		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			
			if(!words.containsKey(token)) { 
				words.put(token, token.length());
			}
		}
		
		Map<String, Integer> result = new TreeMap<>();
		
		int i = 0;
		for(Entry<String, Integer> entry : words.entrySet()) {
			if(i++ < 3) break;
			
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
