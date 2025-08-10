N = "126648"
K = 3

queue = list(N)

while K > 0:
    queue.pop(0)          # pop front
    a = queue.pop(0)      # pop next front
    queue.append(a)       # push to back
    K -= 1

print(queue[0])
