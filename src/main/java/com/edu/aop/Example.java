package com.edu.aop;

import java.util.Scanner;

public class Example {

	private static final Scanner sc = new Scanner(System.in);
	
	
	private static boolean isPrime(int prime) {
		
		if (prime == 1) {
			return false;
		}
		
		for (int i = 2; i < prime; i++) {
			if (prime % i == 0) {
				return false;
			}
		}
		
		return true;
	}
			
	public static void main(String[] args) {
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int prime = sc.nextInt();
			
			while (prime > 0) {
				
			}
			
			if (isPrime(prime)) {
				
			}
			
		}
		
		
		
	}

}
