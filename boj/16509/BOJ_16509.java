package Algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pointer {
	int y,x, cnt;
	Pointer(int sy, int sx, int cnt) {
		y = sy;
		x = sx;
		this.cnt = cnt;
	}
}
class BFS{
	int sy,sx,ey,ex;
	BFS(int ay, int ax, int by, int bx) {
		sy = ay;
		sx = ax;
		ey = by;
		ex = bx;
	}
	int []dy = {2, -2, 2, -2, 3, -3, 3, -3};
	int []dx = {3, 3, -3, -3, -2, -2, 2, 2};
	
	
	void solve() {
		boolean [][]visit = new boolean[10][9];
		Queue<Pointer> q = new LinkedList<Pointer>();
		Pointer pt = new Pointer(sy, sx, 0);
		
		q.add(pt);
		
		while(q.isEmpty() == false) {
			int y = q.peek().y;
			int x = q.peek().x;
			int cnt = q.peek().cnt;
			
			q.poll();
			
			if(y == ey && x == ex) {
				System.out.println(cnt);
				return;
			}
			if(visit[y][x] == true)
				continue;
			
			visit[y][x] = true;
			
			for(int i = 0 ; i < 8; i ++) {
				if(dy[i] == -2 && dx[i] == 3) {
					if((x + 1 == ex && y == ey) || (x + 2 == ex && y - 1 == ey))
						continue;	
				}
				else if(dy[i] == 2 && dx[i] == 3) {
					if((x + 1 == ex && y == ey) || (x + 2 == ex && y + 1 == ey))
						continue;	
				}
				else if(dy[i] == -2 && dx[i] == -3) {
					if((x - 1 == ex && y == ey) || (x - 2 == ex && y - 1 == ey))
						continue;	
				}
				else if(dy[i] == 2 && dx[i] == -3) {
					if((x - 1 == ex && y == ey) || (x - 2 == ex && y + 1 == ey))
						continue;	
				}
				else if(dy[i] == 3 && dx[i] == -2) {
					if((x == ex && y + 1 == ey) || (x - 1 == ex && y + 2 == ey))
						continue;	
				}
				else if(dy[i] == 3 && dx[i] == 2) {
					if((x == ex && y + 1 == ey) || (x + 1 == ex && y + 2 == ey))
						continue;	
				}
				else if(dy[i] == -3 && dx[i] == -2) {
					if((x == ex && y - 1 == ey) || (x - 1 == ex && y - 2 == ey))
						continue;	
				}
				else if(dy[i] == -3 && dx[i] == 2) {
					if((x == ex && y - 1 == ey) || (x + 1 == ex && y - 2 == ey))
						continue;	
				}
				int ny = dy[i] + y;
				int nx = dx[i] + x;
				
				if(0 <= ny && ny <= 9 && 0 <= nx && nx <= 8) {
					Pointer npt = new Pointer(ny,nx, cnt + 1);
					q.add(npt);
				}
			}
		}
	}
	
}

public class BOJ_16509 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a,b,c,d;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		BFS bfs = new BFS(a,b,c,d);
		bfs.solve();
		
	}
}
