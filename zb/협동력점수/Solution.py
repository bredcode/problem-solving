import itertools

answer = int(1e9)

# 최종 풀이 함수
def solution(N, K, arr):
    global answer
    # 중복 순열을 생성 (itertools.product 사용)
    permutations = itertools.product(range(K), repeat=N)

    for perm in permutations:
        # 각 그룹 생성
        groups = [[] for _ in range(K)]

        # 각 학생을 그룹에 배정
        for i in range(N):
            groups[perm[i]].append(arr[i])

        # 1명도 배치되지 않은 그룹이 있는 경우 무시
        if any(len(group) == 0 for group in groups):
            continue

        # 그룹의 전투력을 계산
        min_score = int(1e9)  # 가장 전투력이 낮은 그룹
        max_score = int(-1e9)  # 가장 전투력이 높은 그룹
        for group in groups:
            score = sum(len(group) * power for power in group)
            min_score = min(min_score, score)
            max_score = max(max_score, score)

        # 전투력 차이의 최솟값 계산
        answer = min(answer, abs(min_score - max_score))

    return answer