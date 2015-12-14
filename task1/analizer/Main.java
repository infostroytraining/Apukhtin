package apukhtin.courses.task1.analizer;

import java.io.IOException;

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
