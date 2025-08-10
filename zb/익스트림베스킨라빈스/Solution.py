# 전역변수 선언
r = 0
target = 0
memo = {}

# 사용된 수의 상태를 문자열로 변환
def get_used_state(used):
    return ''.join('1' if u else '0' for u in used)

# 현재 게임 상태에서 현재 사람이 이길 수 있는지 재귀적으로 판단
def dfs(current_sum, used):
    global r, target, memo

    if current_sum >= target:
        return False  # current_sum은 이전 사람이 만들어온 합계이며, 게임이 끝났으므로 현재 사람이 패배

    # 현재 상태를 문자열로 변환하여 메모이제이션 사용
    state = f"{current_sum}:{get_used_state(used)}"

    if state in memo:
        return memo[state]

    # 현재 사람이 이길 수 있는지 판단
    for i in range(1, r + 1):
        if not used[i]:  # 아직 사용하지 않은 수를 선택
            used[i] = True  # 수를 사용
            opponent_can_win = dfs(current_sum + i, used)
            used[i] = False  # 수를 다시 사용 가능하게 돌려놓음

            # 상대방이 이길 수 없으면 현재 사람이 승리
            if not opponent_can_win:
                memo[state] = True
                return True

    # 현재 사람이 이길 수 없는 경우, False를 반환
    memo[state] = False
    return False

# 최종 솔루션 함수
def solution(target_input, r_input):
    global r, target, memo
    r = r_input
    target = target_input
    memo = {}

    # 시작 시 게임 수는 0부터, 사용할 수 있는 수는 1부터 r까지
    used = [False] * (r + 1)
    return dfs(0, used)
