import itertools

# 전역 변수로 N과 enemies 선언
N = 0
enemies = []
answer = int(1e9)

# 모든 중복 순열을 탐색하며 최소 데미지를 계산하는 함수
def calculate_min_damage():
    global answer
    # 3가지 선택지: 0(가만히), 1(좌회전), 2(우회전)의 중복 순열 생성
    for selected in itertools.product([0, 1, 2], repeat=N):
        view = 0  # 처음에 상(위쪽) 방향을 보고 있음
        result = 0
        for i in range(N):
            # 선택지에 따라서 주인공의 방향 변경
            if selected[i] == 1:  # 좌회전
                view = (view - 1 + 4) % 4
            elif selected[i] == 2:  # 우회전
                view = (view + 1) % 4

            # 상(0), 우(1), 하(2), 좌(3)의 위치를 확인하며
            for j in range(4):
                power = enemies[j][i]  # 해당 위치의 적군의 공격력
                # 앞에 존재하는 적은 공격력의 1배수
                if view == j:
                    result += power
                # 오른쪽에 존재하는 적은 공격력의 2배수
                elif (view + 1) % 4 == j:
                    result += power * 2
                # 뒤쪽에 존재하는 적은 공격력의 3배수
                elif (view + 2) % 4 == j:
                    result += power * 3
                # 왼쪽에 존재하는 적은 공격력의 2배수
                else:
                    result += power * 2
        # 차감되는 점수의 합의 최솟값 계산
        answer = min(answer, result)

# 적군이 등장하는 횟수(N)와 적군의 공격력 정보 배열(enemies)을 전역 변수로 설정
def solution(inputN, inputEnemies):
    global N, enemies, answer
    N = inputN
    enemies = inputEnemies
    answer = int(1e9)

    # 모든 중복 순열을 탐색하며 최소 데미지 계산
    calculate_min_damage()
    return answer