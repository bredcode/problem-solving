#include <queue>
 
#define MAX_NODE 26
 
using namespace std;
 
struct Trie{
    Trie *next[MAX_NODE];
    bool isEnd;
};
 
struct INFO{
    Trie *cur;
    int idx;
};
Trie triePool[100002];
int trieIdx;
Trie *root;
Trie *trieAlloc(){
    for(int i = 0 ; i < MAX_NODE; i++)
        triePool[trieIdx].next[i] = nullptr;
    triePool[trieIdx].isEnd = false;
    
    return &triePool[trieIdx++];
}
 
class WordDictionary {
public:
    /** Initialize your data structure here. */
    WordDictionary() {
        trieIdx = 0;
        root = trieAlloc();
    }
    
    /** Adds a word into the data structure. */
    void addWord(string word) {
        Trie *cur = root;
        int len = word.size();
        for(int i = 0 ; i < len; i ++){
            char ch = word[i] - 'a';
            
            if(!cur->next[ch])
                cur->next[ch] = trieAlloc();
            
            cur = cur->next[ch];
        }
        cur->isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    bool search(string word) {
        int len = word.size();
        
        queue<INFO> q;
        
        q.push({root, 0});
        
        while(!q.empty()){
            Trie *cur = q.front().cur;
            int idx = q.front().idx;
            char ch = word[idx];
            
            if(idx == len && cur->isEnd)
                return true;
                
            if(ch != '.') 
                ch -= 'a';
            
            q.pop();
            
            for(int i = 0 ; i < MAX_NODE; i++) {
                if(ch == '.' && cur->next[i]) 
                    q.push({cur->next[i], idx + 1});
                else if(cur->next[i] && i == ch)
                    q.push({cur->next[i], idx + 1});
            }
        }
        
        return false;
    }
};
 
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */