package Algorithm;

import java.util.Comparator;
import java.util.Scanner;

class pair {
	int a;
	int b;
}
class Eratosthenes {
	boolean []isPrime = new boolean[10001];
	int idx;
	void init() {
		for(int i = 0 ; i <= 10000; i ++)
			isPrime[i] = true;
		
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i * i <= 10000; i++) {
			if(isPrime[i])
				for(int j = i * i; j <= 10000; j += i) {
					isPrime[j] = false;
				}
		}
	}
	void solve(int n) {
		for(int i = n / 2; i >= 0; i--) {
			if(isPrime[i] && isPrime[n - i]) {
				System.out.println(i + " " + (n - i));
				return;
			}
		}
	}
}
public class BOJ_9020 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Eratosthenes era = new Eratosthenes();
		era.init();
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int n = sc.nextInt();
			era.solve(n);
		}
	}
}
