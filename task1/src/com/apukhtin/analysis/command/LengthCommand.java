package com.apukhtin.analysis.command;

import com.apukhtin.analysis.calculations.TextAnalizer;

import java.util.Map;

public class LengthCommand implements Command {

	@Override
	public void execute(String text, boolean inParallel) {
		Map<String, Integer> res = TextAnalizer.length(text, inParallel);

		for (Map.Entry<String, Integer> entry : res.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

}
