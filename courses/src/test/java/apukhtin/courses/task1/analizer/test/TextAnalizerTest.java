package apukhtin.courses.task1.analizer.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import apukhtin.courses.task1.analizer.TextAnalizer;

public class TextAnalizerTest {

	@Test
	public void testFrequency() {
		assertEquals(new HashMap<String, Integer>(){
			{
				put("тут", 2);
				put("ашаМ", 2);
			}
		}, TextAnalizer.frequency("Маша тут. Маша любит маша тут кушать кушать"));
	}
	@Test
	public void testLength() {
		assertEquals(new HashMap<String, Integer>() {
			{
				put("вкусный", 7);
				put("коля", 4);
				put("хлеб", 4);
			}
		}, TextAnalizer.length("Коля ел вкусный хлеб. ваще!. хлеб!!!"));
	}

	@Test
	public void testDuplicates() {
		assertEquals(new ArrayList<String>(1) {
			{
				add("ЛЕ");
				add("БЕЛХ");
			}
		}, TextAnalizer.duplicates("Коля ел вкусный хлеб. ваще!. ел хлеб!!!"));
	}

}
