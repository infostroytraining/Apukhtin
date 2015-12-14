package com.apukhtin.analysis.command;

import java.util.Map;
import java.util.Map.Entry;

import apukhtin.courses.task1.analizer.ElaplseTimeCounter;
import apukhtin.courses.task1.analizer.TextAnalizer;

public class LengthCommand implements Command {

	@Override
	public void execute(String text) {
		ElaplseTimeCounter.start();
		
		Map<String, Integer> res = TextAnalizer.length(text);
		
		for(Entry<String, Integer> entry : res.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		
		ElaplseTimeCounter.end();
	}

}
