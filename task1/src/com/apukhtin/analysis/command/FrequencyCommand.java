package com.apukhtin.analysis.command;

import com.apukhtin.analysis.calculations.TextAnalizer;

import java.util.Map;
import java.util.Map.Entry;

public class FrequencyCommand implements Command {

	@Override
	public void execute(String text, boolean inParallel) {
		Map<String, Integer> res = TextAnalizer.frequency(text, inParallel);
		
		for(Entry<String, Integer> entry : res.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}
