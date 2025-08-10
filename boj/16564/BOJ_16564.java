package Algorithm;
import java.util.Scanner;
import java.lang.Math;
public class BOJ_16564 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int arr[] = new int[n];
		int start = 1000000002;
		int end = -1;
		for(int i = 0 ; i < n; i++) {
			arr[i] = sc.nextInt();
			start = Math.min(start, arr[i]);
			end = Math.max(end, arr[i]);
		}
		int ans = start;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int tmp = k;
			for(int i = 0; i < n; i ++) {
				if(arr[i] < mid) {
					tmp -= (mid - arr[i]);
					if(tmp < 0)
						break;
				}
			}
			if(tmp < 0) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
				ans = Math.max(ans, mid);
			}
		}
		System.out.println(ans);
	}
}
