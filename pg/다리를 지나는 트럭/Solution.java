import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
  public int solution(int bridge_length, int weight, int[] truck_weights) {
    ArrayDeque<Integer> bridge = new ArrayDeque<>();
    // 다리 위 상태를 0으로 bridge_length만큼 초기화
    for (int i = 0; i < bridge_length; i++) {
      bridge.offer(0);
    }

    int time = 0;
    int bridgeWeight = 0;
    int idx = 0; // 다음에 올릴 트럭 인덱스

    while (idx < truck_weights.length) {
      time++;

      // 맨 앞(다리에서 내려가는 위치) 처리
      bridgeWeight -= bridge.pollFirst();

      int nextTruck = truck_weights[idx];
      if (bridgeWeight + nextTruck <= weight) {
        bridge.offerLast(nextTruck);
        bridgeWeight += nextTruck;
        idx++;
      } else {
        // 못 올리면 빈 자리 유지
        bridge.offerLast(0);
      }
    }

    // 마지막 트럭이 다리를 완전히 빠져나가려면 다리 길이만큼 추가 시간 필요
    return time + bridge_length;
  }
}