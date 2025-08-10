from collections import deque

N = 6
K = 2

queue = deque(range(1, N+1))
ret = []

while queue:
    for _ in range(K-1):
        queue.append(queue.popleft())  # 앞에서 꺼내 뒤로
    ret.append(queue.popleft())        # 제거

print(list(ret))  # [2, 4, 6, 3, 1, 5]
