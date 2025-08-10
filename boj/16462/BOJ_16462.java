package Algorithm;
import java.util.Scanner;

public class BOJ_16462 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int ans = 0;
		int n = sc.nextInt();
		
		for(int i = 0; i < n ; i ++) {
			String str = sc.next();
			str = str.replace('0', '9');
			str = str.replace('6', '9');
			
			if(Integer.parseInt(str) >= 100)
				ans += 100;
			else
				ans += Integer.parseInt(str);
		}
		System.out.println((int)((1.0 * ans / n) + 0.5));
	}
}
