#include <stdio.h>

int arr[102][102], dist[102][102];
int n;

void dfs(int start, int here, int canGo)
{
	if (canGo)
		dist[start][here] = 1;

	for (int i = 0; i < n; i++)
		if (arr[here][i] == 1 && dist[start][i] == 0)
			dfs(start, i, 1);
}

int main()
{
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			scanf("%d", &arr[i][j]);
	for (int i = 0; i < n; i++)
		dfs(i, i, 0);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%d ", dist[i][j]);
		}
		printf("\n");
	}

	return 0;
}