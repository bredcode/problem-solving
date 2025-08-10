let answer = 1e9;
let permutations = [];

// N명의 학생을 K개의 그룹에 배치하는 중복 순열을 미리 계산
function generatePermutations(N, K, idx, current) {
    // 중복순열이 만들어지면 저장
    if (idx === N) {
        permutations.push([...current]);
        return;
    }
    for (let i = 0; i < K; i++) {
        current[idx] = i;
        generatePermutations(N, K, idx + 1, current);
    }
}

// 최종 풀이 함수
function solution(N, K, arr) {
    let current = new Array(N).fill(0);
    generatePermutations(N, K, 0, current);

    for (let perm of permutations) {
        // 각 그룹 생성
        let groups = [];
        for (let i = 0; i < K; i++) {
            groups.push([]);
        }

        // 각 학생을 그룹에 배정
        for (let i = 0; i < N; i++) {
            groups[perm[i]].push(arr[i]);
        }

        // 1명도 배치되지 않은 그룹이 있는 경우 무시
        let isValid = true;
        for (let i = 0; i < K; i++) {
            if (groups[i].length === 0) {
                isValid = false;
                break;
            }
        }
        if (!isValid) continue;

        // 그룹의 전투력을 계산
        let minScore = 1e9; // 가장 전투력이 낮은 그룹
        let maxScore = -1e9; // 가장 전투력이 높은 그룹
        for (let i = 0; i < K; i++) {
            let score = 0;
            for (let j = 0; j < groups[i].length; j++) {
                let power = groups[i][j];
                score += groups[i].length * power;
            }
            minScore = Math.min(minScore, score);
            maxScore = Math.max(maxScore, score);
        }

        // 전투력 차이의 최솟값 계산
        answer = Math.min(answer, Math.abs(minScore - maxScore));
    }

    return answer;
}