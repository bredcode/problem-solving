def solution(N, K, arr, queries):
    # 이진 탐색 기본 조건: 배열이 정렬되어 있어야 함
    arr.sort()

    result = []

    # 각 쿼리에 대해 처리
    for i in range(K):
        x = queries[i][0]
        y = queries[i][1]

        # x 이상인 첫 번째 인덱스 찾기
        left = lower_bound(arr, x)

        # y 이하인 마지막 인덱스 찾기
        right = upper_bound(arr, y)

        # 구간 내에 있는 원소의 개수 계산
        result.append(right - left)

    return result

# target 이상인 값이 처음으로 나오는 인덱스를 찾음 (lower bound)
def lower_bound(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return left

# target 이하인 값이 마지막으로 나오는 인덱스를 찾음 (upper bound)
def upper_bound(arr, target):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] > target:
            right = mid - 1
        else:
            left = mid + 1
    return left