#include <iostream>
#include <cstdio>
#include <map>

using namespace std;

typedef pair<int, int> pii;

map<pii, char> mp;

int main(){
  int n,m;
  scanf("%d %d",&n,&m);

  for(int i = 0 ; i < m; i ++){
    int a, b;
    char c;
    scanf("%d %d %c",&a,&b,&c);

    mp[{b,a}] = c;
  }

  for(auto it = mp.begin(); it != mp.end(); it++){
    printf("%c",it->second);
  }
  return 0;
}