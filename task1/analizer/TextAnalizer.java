package apukhtin.courses.task1.analizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class TextAnalizer {

	public static Map<String, Integer> frequency(String text) {	
		if(text == null) return new HashMap<>();		
		
		Map<String, Integer> words = new HashMap<>();
		HashMap<String, Integer> result = new HashMap<>();
		
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			
			if(words.containsKey(token)) { 
				words.replace(token, words.get(token) + 1);
			} else {
				words.put(token, 1);
			}
		}
		
		for(int i = 0; i < 2; i++ ) {
			Entry<String, Integer> entryWithMaxVal = words.entrySet()
					.stream()
					.max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.get();
			
			result.put(new StringBuilder(entryWithMaxVal.getKey()).reverse().toString(),
					entryWithMaxVal.getValue());
			words.remove(entryWithMaxVal.getKey());			
		}
				
		return result;
	}

	public static Map<String, Integer> length(String text) {
		if(text == null) return new HashMap<>();
		
		Comparator<String> comparator = (s1, s2) -> Integer.compare(s2.length(), s1.length());
		
		Map<String, Integer> words = new TreeMap<>(comparator);
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");

		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			
			if(!words.containsKey(token)) { 
				words.put(token, token.length());
			}
		}
		
		Map<String, Integer> result = new TreeMap<>(comparator);
		
		int i = 0;
				
		for(Entry<String, Integer> entry : words.entrySet()) {
			if(++i > 3) break;
			
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static List<String> duplicates(String text) {
		if(text == null) return new ArrayList<String>();
		
		List<String> words = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");
		List<String> result = new ArrayList<>(3);
		
		while(tokenizer.hasMoreTokens()) {
			words.add(tokenizer.nextToken());
		}
		
		List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
		
		// keep only duplicates in words
		distinctWords.forEach( w -> words.remove(w));
		
		int resultCount = 0;
		for(String word : words) {
			if(++resultCount > 3) break;
			
			result.add(new StringBuilder(word).reverse().toString().toUpperCase());
		}
		
		Collections.sort(result, (el1, el2) -> Integer.compare(el1.length(), el2.length()));
		return result;
	}
}
