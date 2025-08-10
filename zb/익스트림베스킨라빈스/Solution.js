let r = 0;
let target = 0;
let memo = {};

// 사용된 수의 상태를 문자열로 변환
function getUsedState(used) {
    return used.map(u => u ? '1' : '0').join('');
}

// 현재 게임 상태에서 현재 사람이 이길 수 있는지 재귀적으로 판단
function dfs(currentSum, used) {
    if (currentSum >= target) {
        return false; // currentSum은 이전 사람이 만들어온 합계이며 게임이 끝났으므로 현재 사람이 패배
    }

    // 현재 상태를 문자열로 변환하여 메모이제이션 사용
    const state = `${currentSum}:${getUsedState(used)}`;

    if (memo[state] !== undefined) {
        return memo[state];
    }

    // 현재 사람이 이길 수 있는지 판단
    for (let i = 1; i <= r; i++) {
        if (!used[i]) {  // 아직 사용하지 않은 수를 선택
            used[i] = true;  // 수를 사용
            const opponentCanWin = dfs(currentSum + i, used);
            used[i] = false;  // 수를 다시 사용 가능하게 돌려놓음

            // 상대방이 이길 수 없으면 현재 사람이 승리
            if (!opponentCanWin) {
                memo[state] = true;
                return true;
            }
        }
    }

    // 현재 사람이 이길 수 없는 경우, false를 반환
    memo[state] = false;
    return false;
}

// 최종 솔루션 함수
function solution(targetInput, rInput) {
    r = rInput;
    target = targetInput;
    memo = {};

    // 시작 시 게임 수는 0부터, 사용할 수 있는 수는 1부터 r까지
    const used = Array(r + 1).fill(false);
    return dfs(0, used);
}
