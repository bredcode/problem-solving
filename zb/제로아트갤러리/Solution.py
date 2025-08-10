def solution(arrive, patience):
    N = len(arrive)
    items = []

    # 도착 시간과 인내심을 튜플로 묶어서 리스트에 저장
    for i in range(N):
        items.append((arrive[i], patience[i]))

    # 도착시간이 빠른 순 -> 인내심이 적은 순으로 정렬
    items.sort(key=lambda x: (x[0], x[1]))

    result = 0

    # 첫 번째 사람은 미리 대기중으로 설정
    last_arrive_time = items[0][0]
    last_patience_time = items[0][1]
    cur_idx = 1

    while cur_idx < N:
        arrive_time = items[cur_idx][0]
        patience_time = items[cur_idx][1]

        # 현재 도착한 사람이 기다리는 사람과 만난 경우 -> 관람
        if last_arrive_time <= arrive_time <= last_arrive_time + last_patience_time:
            result += 2
            cur_idx += 1
            if cur_idx >= N:
                break
            last_arrive_time = items[cur_idx][0]
            last_patience_time = items[cur_idx][1]
            cur_idx += 1
            if cur_idx >= N:
                break
        else:
            # 현재 도착한 사람이 기다려야 하는 경우
            last_arrive_time = arrive_time
            last_patience_time = patience_time
            cur_idx += 1

    return result