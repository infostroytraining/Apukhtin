package com.apukhtin.analysis.analizer.converter;

import com.apukhtin.analysis.command.Command;
import com.apukhtin.analysis.command.DuplicatesCommand;
import com.apukhtin.analysis.command.FrequencyCommand;
import com.apukhtin.analysis.command.LengthCommand;
import com.beust.jcommander.IStringConverter;

public class CommandConverter implements IStringConverter<Command> {
	@Override
	public Command convert(String value) {
		Command result = null;
		
		if(value.equals("length")) result = new LengthCommand();
		if(value.equals("duplicates")) result = new DuplicatesCommand();
		if(value.equals("frequency")) result = new FrequencyCommand();

		if (result == null) {
			throw new IllegalArgumentException("Invalid task set");
		}
		return result;
	}
}
