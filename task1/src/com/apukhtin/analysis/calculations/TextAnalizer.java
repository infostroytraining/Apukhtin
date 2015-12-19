package com.apukhtin.analysis.calculations;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TextAnalizer {

	public static Map<String, Integer> frequency(String text, boolean doInParallel) {
		if(text == null) return new HashMap<>();

		// map of words and their frequency
		Map<String, Integer> words = new HashMap<>();
		//result containing only 2 entries
		HashMap<String, Integer> result = new HashMap<>();
		
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			words.computeIfPresent(token, (s, integer) -> integer + 1);
			words.computeIfAbsent(token, s -> 1);
		}
		
		for(int i = 0; i < 2; i++ ) {
			Stream<Entry<String, Integer>> stream = doInParallel ? words.entrySet().parallelStream()
					: words.entrySet().stream();

			Entry<String, Integer> entryWithMaxVal = stream
					.max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.get();
			
			result.put(new StringBuilder(entryWithMaxVal.getKey()).reverse().toString(),
					entryWithMaxVal.getValue());
			words.remove(entryWithMaxVal.getKey());			
		}
				
		return result;
	}

	public static Map<String, Integer> length(String text, boolean doInParallel) {
		if(text == null) return new HashMap<>();
		
		Comparator<String> comparator = (s1, s2) -> Integer.compare(s2.length(), s1.length());
		
		Map<String, Integer> words = new TreeMap<>(comparator);
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");

		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			words.computeIfAbsent(token, s -> token.length());
		}

		Stream<Map.Entry<String, Integer>> stream = doInParallel ? words.entrySet().parallelStream()
				: words.entrySet().stream();

		words = stream
				.limit(3)
				.collect(Collectors.toMap((Map.Entry<String, Integer> entry) -> entry.getKey(),
						(Map.Entry<String, Integer> entry) -> entry.getValue()));

		return words;
	}

	public static List<String> duplicates(String text, boolean doInParallel) {
		if(text == null) return new ArrayList<String>();
		
		List<String> words = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(text, "?!. ,;:\n\t\f");
		List<String> result = new ArrayList<>(3);
		
		while(tokenizer.hasMoreTokens()) {
			words.add(tokenizer.nextToken());
		}

		Stream<String> stream = doInParallel ? words.parallelStream() : words.stream();

		List<String> distinctWords = stream
				.distinct()
				.collect(Collectors.toList());
		
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
