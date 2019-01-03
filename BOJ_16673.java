package Algorithm;
import java.util.Scanner;

public class BOJ_16673 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int c, k, p;
		c = sc.nextInt();
		k = sc.nextInt();
		p = sc.nextInt();
		
		long ans = 0;
		for(int i = 1; i <= c; i ++) {
			ans += (k*i + p*i*i);
		}
		
		System.out.println(ans);
	}

}
