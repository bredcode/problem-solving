function solution(bridge_length, weight, truck_weights) {
  const bridge = Array(bridge_length).fill(0); // 다리 위 상태를 나타내는 큐
  let time = 0;
  let bridgeWeight = 0;
  let idx = 0; // 다음에 올릴 트럭 인덱스

  // 트럭들을 순서대로 처리
  while (idx < truck_weights.length) {
    time += 1;

    // 맨 앞(다리에서 나가는 위치) 트럭 처리
    const left = bridge.shift();
    bridgeWeight -= left;

    // 다음 트럭을 올릴 수 있는지 확인
    const truck = truck_weights[idx];
    if (bridgeWeight + truck <= weight) {
      bridge.push(truck);
      bridgeWeight += truck;
      idx += 1; // 트럭 하나 올렸으니 다음으로
    } else {
      // 못 올리면 빈 자리(0) 유지
      bridge.push(0);
    }
  }

  // 마지막 트럭이 다리를 완전히 지나가야 하므로 다리 길이만큼 추가 시간
  return time + bridge_length;
}
