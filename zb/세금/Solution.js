function solution(N, K, arr, queries) {
    // 이진 탐색 기본 조건: 배열이 정렬되어 있어야 함
    arr.sort((a, b) => a - b);

    let result = [];

    // 각 쿼리에 대해 처리
    for (let i = 0; i < K; i++) {
        let x = queries[i][0];
        let y = queries[i][1];

        // x 이상인 첫 번째 인덱스 찾기
        let left = lowerBound(arr, x);

        // y 이하인 마지막 인덱스 찾기
        let right = upperBound(arr, y);

        // 구간 내에 있는 원소의 개수 계산
        result.push(right - left);
    }

    return result;
}

// target 이상인 값이 처음으로 나오는 인덱스를 찾음 (lower bound)
function lowerBound(arr, target) {
    let left = 0, right = arr.length - 1;
    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return left;
}

// target 이하인 값이 마지막으로 나오는 인덱스를 찾음 (upper bound)
function upperBound(arr, target) {
    let left = 0, right = arr.length - 1;
    while (left <= right) {
        let mid = Math.floor((left + right) / 2);
        if (arr[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return left;
}