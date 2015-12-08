package apukhtin.courses.task1;

import java.util.StringTokenizer;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

	public String correctText(String s) {
		StringBuilder text = new StringBuilder(s);

		final String SENTANCE_DELIMS = "!.?";
		final String PUNCTIATION_DELIMS = ",.";

		// position all spaces after punctial delims.
		for (int i = 0; i < text.length(); i++) {
			char curChar = text.charAt(i);

			if (PUNCTIATION_DELIMS.contains(String.valueOf(curChar)) && i + 1 < text.length()
					&& text.charAt(i + 1) != ' ') {
				text.insert(++i, ' '); // ++i means that current delim is
										// processes and
										// we have to move ahead
			}

		}

		// If current delim is !.? All spaces are in their positions already
		for (int i = 0; i < text.length(); i++) {
			char curChar = text.charAt(i);

			// expected big letter has index i+2 (considering spaces)| ↓(below)
			// to avoid out of range
			if (SENTANCE_DELIMS.contains(String.valueOf(curChar)) && i + 2 < text.length()
					&& Character.isLowerCase(text.charAt(i + 2))) {
				text.setCharAt(i + 2, Character.toUpperCase(text.charAt(i + 2)));
				i++;
			}
		}

		char lastChar = s.charAt(s.length() - 1);
		char firstChar = s.charAt(0);

		// check 1st letter to be UpperCase
		if (Character.isLowerCase(firstChar))
			text.setCharAt(0, Character.toUpperCase(firstChar));

		// check last char for containing .!?
		if (!SENTANCE_DELIMS.contains(String.valueOf(lastChar))) {
			text.append(".");
		}

		return text.toString();
	}
}