#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
int N, K;
long long maxV = -1000000000;
long long minV = 1000000000;
vector <long long> vc;
int main() {
	scanf("%d %d", &N, &K);
	for (int i = 0; i<N; i++) {
		int x;
		scanf("%d", &x);
		if (x>maxV) maxV = x;
		if (x<minV) minV = x;
		vc.push_back(x);
	}
	sort(vc.begin(), vc.end());
	long long l = minV;
	long long r = maxV;
	long long mid = 0;
	long long ans = l;
	while (l <= r) {
		mid = (l + r) / 2;
		long long tSum = 0;
		for (int i = 0; i<N; i++) {
			if (vc[i] < mid) tSum += (mid - vc[i]);
			if (tSum > K)
				break;
		}
		if (tSum <= K) {
			l = mid + 1;
			ans = max(ans, mid);
		}
		else
			r = mid - 1;
	}
	printf("%lld", ans);

	return 0;
}