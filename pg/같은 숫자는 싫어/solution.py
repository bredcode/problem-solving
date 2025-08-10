def solution(arr):
    stack = []
    
    for value in arr:
        if not stack:
            stack.append(value)
        else:
            top = stack[-1]
            if top != value:
                stack.append(value)
            # 같으면 넘어감 (중복 제거)
    
    return stack
