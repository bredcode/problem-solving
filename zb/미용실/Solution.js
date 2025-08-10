class Node {
    constructor(s, e) {
        this.s = s;
        this.e = e;
    }
}

function solution(N, reserved) {
    let arr = [];
    for (let i = 0; i < N; i++) {
        arr.push(new Node(reserved[i][0], reserved[i][1]));
    }

    // 종료가 빠른 순 -> 시작이 빠른 순으로 정렬
    arr.sort((a, b) => {
        if (a.e === b.e) {
            return a.s - b.s;
        }
        return a.e - b.e;
    });

    let answer = 1;  // 처음 예약자 수는 1명으로 시작
    let curIdx = 0;  // 현재 예약자 인덱스

    for (let i = 1; i < N; i++) {
        // 현재 커트가 끝난 후 다음 예약자를 받을 수 있다면 카운트
        if (arr[curIdx].e <= arr[i].s) {
            curIdx = i;
            answer++;
        }
    }

    return answer;
}
