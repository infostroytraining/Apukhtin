package apukhtin.courses.task1.analizer;

import com.beust.jcommander.IStringConverter;

import apukhtin.courses.task1.analizer.command.Command;
import apukhtin.courses.task1.analizer.command.DuplicatesCommand;
import apukhtin.courses.task1.analizer.command.FrequencyCommand;
import apukhtin.courses.task1.analizer.command.LengthCommand;

public class CommandConverter implements IStringConverter<Command> {
	@Override
	public Command convert(String value) {
		Command result = null;
		
		if(value.equals("length")) result = new LengthCommand();
		if(value.equals("duplicates")) result = new DuplicatesCommand();
		if(value.equals("frequency")) result = new FrequencyCommand();
		
		return result;
	}
}
