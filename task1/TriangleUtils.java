package apukhtin.courses.task1;

public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
	 *
	 */

	public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
		if(!areValidArgs(a, b, c)) throw new IllegalArgumentException();
		
		return a + b > c && a + c > b && b + c > a;
	}

	/**
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
	 */

	public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
		if(!areValidArgs(a, b, c)) throw new IllegalArgumentException();
		
		// Heron's formula
		double p = (a + b + c) / 3;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}
	
	private static boolean areValidArgs(int a, int b, int c) {
		if(a < 0 || b < 0 || c < 0) return false;
		if(a == b && b == c && c == 0) return false;
		
		return true;
	}
}