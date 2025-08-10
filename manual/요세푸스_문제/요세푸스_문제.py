N = 6
K = 2

queue = [i for i in range(1, N+1)]  # [1, 2, 3, 4, 5, 6]

ret = []  # 요세푸스 순열 결과

while queue:
    for _ in range(K-1):
        queue.append(queue.pop(0))   # 앞의 원소를 빼서 뒤로 넣기
    ret.append(queue.pop(0))         # K번째 원소를 결과에 넣고 제거

print(ret)  # [2, 4, 6, 3, 1, 5]
