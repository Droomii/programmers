// https://programmers.co.kr/learn/courses/30/lessons/17685

import java.util.*;

class Solution {
    public int solution(String[] words) {
    	Trie root = new Trie();
    	for(String word : words) {
    		root.insertWord(word);
    	}
    	
        int answer = 0;
    	for(String word : words) {
    		answer += root.least(word);
    	}
        return answer;
    }
}

class Trie{
	Map<Character, Trie> trieMap;
	int cnt;
	
	public Trie() {
		trieMap = new HashMap<>();
		cnt = 0;
	}
	
	public void insertWord(String word) {
		Trie subTrie = this;
		subTrie.cnt++;
		for(int i = 0; i < word.length(); i++) {
			subTrie.trieMap.putIfAbsent(word.charAt(i), new Trie());
			subTrie = subTrie.trieMap.get(word.charAt(i));
			subTrie.cnt++;
		}
		
	}
	public int least(String query) {
		return least(query, 0);
	}
	
	public int least(String query, int idx) {
		if(cnt==1 || idx==query.length()) return idx;
		return trieMap.get(query.charAt(idx)).least(query, idx+1);
	}
}