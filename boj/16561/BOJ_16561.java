package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_16561 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int ans = 0;
		for(int i = 3; i <= Math.min(3000, n); i += 3)
			for(int j = 3; j <= Math.min(3000, n); j += 3)
				for(int k = 3; k <= Math.min(3000, n); k += 3)
					if(i + j + k == n)
						ans++;
		
		System.out.println(ans);
	}
}
