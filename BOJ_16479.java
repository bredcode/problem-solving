package Algorithm;
import java.util.Scanner;

public class BOJ_16479 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a,b,c;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		System.out.println(1.0 * a * a - ((1.0 * (b-c) / 2) * (1.0 * (b-c) / 2)));
	}

}
