package com.edu.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//소수 판별 메서드 ( % 연산자로 일의자리를 뽑아서 해결 )
public class Problem06_2 {

	private static boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	//숫자를 뒤집는 메서드
	private static int reverseNumber(int n) {
		int reversedNumber = 0; 
		
		while (n > 0) {
			int units = n % 10; 
			reversedNumber = (reversedNumber * 10) + units;
			n = n / 10;
		}
		
		return reversedNumber;
	}
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		final int n = sc.nextInt();
		final List<Integer> answer = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			int number = sc.nextInt();
			int reversedNumber = reverseNumber(number);
			if (isPrime(reversedNumber)) {
				answer.add(reversedNumber);
			}
		}
		
		answer.forEach(item -> System.out.print(item + " "));
	}

	private static void test1() {
		int n = 910;
		
		//어떤 숫자를 10으로 나눈 나머지를 구하면 일의 자리를 구할 수 있다.
		System.out.println("n = " + n % 10); // 0
		
		//어떤 숫자의 일의 자리를 없애려면 10으로 나는 몫을 구하면 된다.
		n = n / 10;
		System.out.println("n = " + n);
		
		
		System.out.println("n = " + n % 10);
		
		n = n / 10;
		System.out.println("n = " + n);
		
		System.out.println("n = " + n % 10);
		
		n = n / 10;
		System.out.println("n = " + n);
	}
}
