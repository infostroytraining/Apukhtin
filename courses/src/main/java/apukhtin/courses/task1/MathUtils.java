package apukhtin.courses.task1;

import java.util.LinkedList;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
		int minNum, maxNum;

		if (firstNumber > secondNumber) {
			minNum = secondNumber;
			maxNum = firstNumber;
		} else {
			minNum = firstNumber;
			maxNum = secondNumber;
		}

		// Anti "zero dividing exception"
		if(minNum == 0) return 0;
		
		return maxNum % minNum == 0 ? minNum : getGreatestCommonDivider(maxNum - minNum, minNum);
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		int result = 0;

		do {
			result += number % 10;
		}
		while ((number /= 10) != 0);

		return result;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		final int PRIME_DIV_COUNT = 2;

		return getNumDivisorsCount(number) == PRIME_DIV_COUNT;
	}

	private static int getNumDivisorsCount(int n) {
		int result = 2; // 1 and itself

		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				result++;
		}

		return result;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) {
		int result = 0;

		for (int i = 1; i <= n; i++) {
			int value = fact(i);

			result += i % 2 == 0 ? -value : value;
		}

		return result;
	}

	public static int fact(int n) {
		if (n < 2)
			return 1;

		int result = 1;

		for (int i = 2; i <= n; i++)
			result *= i;

		return result;

	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) {
		if(length <= 0) return new int[]{};
		
		int[] result = new int[length];
		
		for (int i = 0; i < length; i++) {
			result[i] = fib(i);
		}
		
		return result;
	}

	private static int fib(int n) {
		// Ineffective but one-lined :P
		return n < 2 ? 1 : fib(n - 1) + fib(n - 2);
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) {
		if (length <= 0)
			return new int[] {};

		LinkedList<Integer> res = new LinkedList<>();

		for (int i = 2; res.size() < length; i++) {
			if(isPrime(i)) {
				res.add(i);
			}
		}
		
		// Integer[] -> int[]
		return res.stream().mapToInt(Integer::intValue).toArray();
	}
}