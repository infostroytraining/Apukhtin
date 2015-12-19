package com.apukhtin.analysis.command;

import com.apukhtin.analysis.calculations.TextAnalizer;

import java.util.List;

public class DuplicatesCommand implements Command {

	@Override
	public void execute(String text, boolean inParallel) {
		List<String> res = TextAnalizer.duplicates(text, inParallel);
		res.forEach(el -> System.out.println(el));
	}

}
