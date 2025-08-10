let minTime = Infinity;
let N = 0;
let boss = [];
let skill = [];

function solution(_N, _boss, _skill) {
    // 전역 변수에 값 할당
    N = _N;
    boss = _boss.slice();  // 배열 복사
    skill = _skill.slice();  // 배열 복사
    minTime = Infinity;

    // 방문 여부 리스트 초기화
    let visited = new Array(N).fill(false);

    // DFS 탐색 시작
    battle(visited, [], 0, 0);

    return minTime;
}

// 가능한 모든 보스 처치 순서를 계산하는 DFS 함수
function battle(visited, currentOrder, currentSkill, currentTime) {
    // 현재 시간이 이미 최소 시간보다 크다면 중지 (가지치기)
    if (currentTime > minTime) {
        return;
    }

    // 모든 보스를 처치한 경우, 최소 시간 갱신
    if (currentOrder.length === N) {
        minTime = Math.min(minTime, currentTime);
        return;
    }

    // 아직 방문하지 않은 보스 탐색
    for (let i = 0; i < N; i++) {
        if (!visited[i]) {
            visited[i] = true;
            currentOrder.push(i);

            // 해당 보스를 처치하는 데 걸리는 시간 계산
            let battleTime = Math.max(0, boss[i] - currentSkill);

            // 다음 보스를 탐색
            battle(visited, currentOrder, skill[i], currentTime + battleTime);

            // 상태 복구
            currentOrder.pop();
            visited[i] = false;
        }
    }
}