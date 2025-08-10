function solution(N, _warriors) {
    let answer = 0;

    // i번째 전사를 제거 예정
    for (let i = 0; i < N; i++) {
        // 모든 전사를 추가
        let warriors = [..._warriors]; // 배열 복사

        // i번째 전사 제거
        warriors.splice(i, 1);

        // 자신감 계산
        let count = confidence(N - 1, warriors);
        answer = Math.max(answer, count);
    }

    return answer;
}

function confidence(M, arr) {
    let cnt = 0; // 자신감 점수
    for (let i = 0; i < M; i++) {
        let left = i - 1; // 왼쪽 전사
        if (left === -1) left = M - 1;

        let right = i + 1; // 오른쪽 전사
        if (right === M) right = 0;

        // 자신감이 있는 상태인 전사인 경우
        if (arr[left] < arr[i] && arr[i] > arr[right]) {
            cnt += 1; // 카운트
        }
    }
    return cnt;
}