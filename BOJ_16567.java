package Algorithm;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_16567 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int l = sc.nextInt();
		int arr[] =  new int[n];
		
		for(int i = 0 ; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int i = 0 ; i < n; i ++) {
			if(arr[i] <= l)
				l++;
			else
				break;
		}
		System.out.println(l);
	}
}
