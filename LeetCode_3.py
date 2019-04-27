from collections import deque

class Solution :
	def lengthOfLongestSubstring(self, s: str) -> int :
	cur = 0
	ans = 0
	dq = deque()
	length = len(s)
	dic = {}
	for i in range(0, length) :
		if s[i] not in dic :
dq.append(s[i])
dic[s[i]] = 1
cur += 1
ans = max(ans, cur)
		else:
print(i, s[i])
while dq and dq[0] != s[i] :
	left = dq.popleft()
	del dic[left]
	cur -= 1
	left = dq.popleft()
	del dic[left]
	cur -= 1

	dic[s[i]] = 1
	dq.append(s[i])
	cur += 1
	ans = max(ans, cur)

	return ans