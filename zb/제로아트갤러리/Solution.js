function solution(arrive, patience) {
    const N = arrive.length;
    let items = [];

    // 도착 시간과 인내심을 배열로 묶어서 리스트에 저장
    for (let i = 0; i < N; i++) {
        items.push([arrive[i], patience[i]]);
    }

    // 도착시간이 빠른 순 -> 인내심이 적은 순으로 정렬
    items.sort((a, b) => {
        if (a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    });

    let result = 0;

    // 첫 번째 사람은 미리 대기중으로 설정
    let lastArriveTime = items[0][0];
    let lastPatienceTime = items[0][1];
    let curIdx = 1;

    while (curIdx < N) {
        let arriveTime = items[curIdx][0];
        let patienceTime = items[curIdx][1];

        // 현재 도착한 사람이 기다리는 사람과 만난 경우 -> 관람
        if (lastArriveTime <= arriveTime && arriveTime <= lastArriveTime + lastPatienceTime) {
            result += 2;
            curIdx += 1;
            if (curIdx >= N) break;
            lastArriveTime = items[curIdx][0];
            lastPatienceTime = items[curIdx][1];
            curIdx += 1;
            if (curIdx >= N) break;
        } else {
            // 현재 도착한 사람이 기다려야 하는 경우
            lastArriveTime = arriveTime;
            lastPatienceTime = patienceTime;
            curIdx += 1;
        }
    }

    return result;
}