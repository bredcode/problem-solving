def solution(N, _warriors):
    answer = 0

    # i번째 워리어를 제거 예정
    for i in range(N):
        # 일단 모든 워리어 추가
        warriors = _warriors[:]

        # i번째 워리어 제거
        warriors.pop(i)

        # 자신감 계산
        count = confidence(N - 1, warriors)
        answer = max(answer, count)

    return answer

def confidence(M, arr):
    cnt = 0  # 자신감 점수
    for i in range(M):
        left = i - 1  # 왼쪽 전사
        if left == -1:
            left = M - 1
        right = i + 1  # 오른쪽 전사
        if right == M:
            right = 0
        # 자신감이 있는 상태인 전사인 경우
        if arr[left] < arr[i] and arr[i] > arr[right]:
            cnt += 1  # 카운트

    return cnt