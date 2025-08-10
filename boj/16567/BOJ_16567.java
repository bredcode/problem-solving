package Algorithm;
import java.util.Scanner;

public class BOJ_16567 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int arr[] =  new int[n];
		
		int ans = 0;
		
		for(int i = 0 ; i < n; i++)
			arr[i] = sc.nextInt();
		
		boolean chk = false;
		for(int i = 0 ; i < n ; i ++) {
			
			if(arr[i] == 1)
				chk = true;
			else if(chk == true && arr[i] == 0) {
				ans++;
				chk = false;
			}
		}
		if(chk)
			ans++;
		
		for(int i = 0 ; i < m; i ++) {
			int a, b;
			a = sc.nextInt();
			if(a == 0) {
				System.out.println(ans);
			}
			else {
				b = sc.nextInt();
				b--;
				if(arr[b] == 1)
					continue;
				
				arr[b] = 1;
				
				int cnt = 0;
				if(b - 1 >= 0 && arr[b - 1] == 1) {
					cnt++;
				}
				if(b + 1 < n && arr[b + 1] == 1) {
					cnt++;
				}
				
				if(cnt == 2)
					ans--;
				else if(cnt == 1)
					continue;
				else if(cnt == 0)
					ans++;
			}
		}
	}
}
