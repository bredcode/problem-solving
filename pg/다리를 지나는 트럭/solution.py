from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)  # 다리 위의 트럭들 초기화
    time = 0
    bridge_weight = 0  # 현재 다리 위의 총 무게

    for truck in truck_weights:
        while True:
            time += 1
            bridge_weight -= bridge.popleft()  # 다리의 맨 앞 트럭 나가기

            # 트럭을 다리에 올릴 수 있는지 확인
            if bridge_weight + truck <= weight:
                bridge.append(truck)
                bridge_weight += truck
                break
            else:
                bridge.append(0)  # 다리에 트럭이 못 올라오면 빈 자리를 유지

    # 마지막 트럭이 다리를 모두 건너기 위해 다리 길이만큼 추가 시간 필요
    return time + bridge_length
