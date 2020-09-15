// https://programmers.co.kr/learn/courses/30/lessons/12981

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
    	Set<String> used = new HashSet<>();
    	String lastWord = words[0];
    	used.add(lastWord);
    	for(int i = 1; i < words.length;i++) {
    		String word = words[i];
    		if(used.contains(word) || (lastWord.charAt(lastWord.length()-1) != word.charAt(0))) {
    			int no = i%n+1;
    			int turn = i/n+1;
    			return new int[] {no, turn};
    		}
    		used.add(word);
    		lastWord = word;
    	}
        return new int[] {0, 0};
    }
}