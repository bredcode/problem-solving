#include<iostream>
#include<cstdio>
using namespace std;
 
struct Tree {
    int left = 0;
    int right = 0;
};

// 10^6보다 작은 양의 정수이기에 0을 초기값으로 둘 수 있다.
Tree tree[1000002];
 
// 후위 순회
void postOrder(int node) {
    if (tree[node].left != 0) {
        postOrder(tree[node].left);
    }
    if (tree[node].right != 0) {
        postOrder(tree[node].right);
    }
    printf("%d\n", node);
}
 
int main() {
    int root;
    int value;
    scanf("%d", &root);
    while (scanf("%d", &value)!=EOF) {
        int node = root;
        while (true) {
            if (node < value) {
                if (tree[node].right != 0) {
                    node = tree[node].right;
                }
                else {
                    tree[node].right = value;
                    break;
                }
            }
            else {
                if (tree[node].left != 0) {
                    node = tree[node].left;
                }
                else {
                    tree[node].left = value;
                    break;
                }
            }
        }
    }
    postOrder(root);
    return 0;
}