package com.apukhtin.analysis;

import com.apukhtin.analysis.analizer.AnalizerController;
import com.apukhtin.analysis.analizer.ArgsAnalizer;
import com.apukhtin.analysis.timecount.ElaplseTimeCounter;
import com.beust.jcommander.JCommander;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		ArgsAnalizer argsAnalizer = new ArgsAnalizer();

		new JCommander(argsAnalizer, args);

		try {
			ElaplseTimeCounter.start();
			AnalizerController.process(argsAnalizer);
			ElaplseTimeCounter.end();
		} catch (IOException e) {
			System.out.println("Error:" + e.getClass() + " " + e.getMessage());
		}
	}

}
