class Node:
    def __init__(self, s, e):
        self.s = s
        self.e = e

def solution(N, reserved):
    arr = []
    for i in range(N):
        arr.append(Node(reserved[i][0], reserved[i][1]))

    # 종료가 빠른 순 -> 시작이 빠른 순으로 정렬
    arr.sort(key=lambda x: (x.e, x.s))

    answer = 1  # 처음 예약자 수는 1명으로 시작
    cur_idx = 0  # 현재 예약자 인덱스

    for i in range(1, N):
        # 현재 커트가 끝난 후 다음 예약자를 받을 수 있다면 카운트
        if arr[cur_idx].e <= arr[i].s:
            cur_idx = i
            answer += 1

    return answer
