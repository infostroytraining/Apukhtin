package com.apukhtin.analysis;

import java.io.IOException;

import com.apukhtin.analysis.analizer.AnalizerController;
import com.apukhtin.analysis.analizer.ArgsAnalizer;
import com.beust.jcommander.JCommander;

public class Main {

	public static void main(String[] args) {
		ArgsAnalizer argsAnalizer = new ArgsAnalizer();
		
		new JCommander(argsAnalizer, args);
		
		try {
			AnalizerController.process(argsAnalizer);
		} catch (IOException e) {
			System.out.println("Error:" + e.getClass() + " " + e.getMessage());
		}
	}

}
