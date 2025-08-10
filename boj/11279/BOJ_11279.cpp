#include <iostream>
#include <queue>
#include <cstdio>

using namespace std;
priority_queue<int> pq;

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		int val;
		scanf("%d", &val);

		if (val)
			pq.push(val);

		else if (!val)
		{
			if (pq.empty())
				printf("0\n");

			else
			{
				printf("%d\n", pq.top());
				pq.pop();
			}
		}
	}
	return 0;
}