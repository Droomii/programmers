import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
    public int[] solution(String[] words, String[] queries) {
    	Map<Integer, NavigableSet<String>> wordMap = new HashMap<>();
    	Map<Integer, NavigableSet<String>> wordRevMap = new HashMap<>();
    	Arrays.stream(words).forEach(a -> {
    		int len = a.length();
    		NavigableSet<String> wordByLen = wordMap.getOrDefault(len, new TreeSet<String>());
    		wordByLen.add(a);
    		wordMap.put(len, wordByLen);
    		wordByLen = wordRevMap.getOrDefault(len, new TreeSet<String>());
    		wordByLen.add(new StringBuilder(a).reverse().toString());
    		wordRevMap.put(len, wordByLen);
    	});
    	int[] answer = new int[queries.length];
    	Map<String, Integer> duplicateQuery = new HashMap<>();
    	for(int i = 0 ; i < queries.length; i++) {
    		String word = queries[i];
    		try {
    			answer[i] = duplicateQuery.get(queries[i]);
    			continue;
    		}catch(Exception e) {}
    		try{
    			int wordLen = word.length();
        		NavigableSet<String> wordPool = wordMap.get(wordLen);
        		StringBuilder sb = new StringBuilder(word.replaceAll("\\?", ""));
        		if(word.startsWith("?")) {
        			 wordPool = wordRevMap.get(wordLen);
        			 sb.reverse();
        		}
        		String q = sb.toString();
        		
    			wordPool = wordPool.tailSet(q, true).headSet(q + '{', true);
    			answer[i] = wordPool.size();
    			duplicateQuery.put(word, wordPool.size());
    		}catch (Exception e) {
    			duplicateQuery.put(word, 0);
    			answer[i] = 0;
			}
    	}
        return answer;
    }
}
