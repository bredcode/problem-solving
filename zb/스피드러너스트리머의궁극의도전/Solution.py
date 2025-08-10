min_time = float('inf')
N = 0
boss = []
skill = []

def solution(_N, _boss, _skill):
    global N, boss, skill, min_time
    # 전역 변수에 값 할당
    N = _N
    boss = _boss
    skill = _skill
    min_time = float('inf')

    # 방문 여부 리스트 초기화
    visited = [False] * N

    # DFS 탐색 시작
    battle(visited, [], 0, 0)

    return min_time

# 가능한 모든 보스 처치 순서를 계산하는 DFS 함수
def battle(visited, current_order, current_skill, current_time):
    global min_time

    # 현재 시간이 이미 최소 시간보다 크다면 중지 (가지치기)
    if current_time > min_time:
        return

    # 모든 보스를 처치한 경우, 최소 시간 갱신
    if len(current_order) == N:
        min_time = min(min_time, current_time)
        return

    # 아직 방문하지 않은 보스 탐색
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            current_order.append(i)

            # 해당 보스를 처치하는 데 걸리는 시간 계산
            battle_time = max(0, boss[i] - current_skill)

            # 다음 보스를 탐색
            battle(visited, current_order, skill[i], current_time + battle_time)

            # 상태 복구
            current_order.pop()
            visited[i] = False