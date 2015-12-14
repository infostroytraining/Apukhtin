package com.apukhtin.analysis.command;

import java.util.List;

import apukhtin.courses.task1.analizer.ElaplseTimeCounter;
import apukhtin.courses.task1.analizer.TextAnalizer;

public class DuplicatesCommand implements Command {

	@Override
	public void execute(String text) {
		ElaplseTimeCounter.start();
		
		List<String> res = TextAnalizer.duplicates(text);
		res.forEach( el -> System.out.println(el) );
		
		ElaplseTimeCounter.end();
		
	}

}
