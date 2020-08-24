import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Solution {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		Map<Integer, NavigableSet<String>> wordByLen = new HashMap<Integer, NavigableSet<String>>();
		Map<Integer, NavigableSet<String>> wordByLenReverse = new HashMap<Integer, NavigableSet<String>>();
		
		for(String word : words) {
			NavigableSet<String> wordSet = wordByLen.getOrDefault(word.length(),new TreeSet<String>());
			NavigableSet<String> wordSetReverse = wordByLenReverse.getOrDefault(word.length(),new TreeSet<String>());
			wordSet.add(word);
			StringBuilder sb = new StringBuilder(word);
			wordSetReverse.add(sb.reverse().toString());
			wordByLen.putIfAbsent(word.length(), wordSet);
			wordByLenReverse.putIfAbsent(word.length(), wordSetReverse);
		}
		Map<String, Integer> doneQuery = new HashMap<>();
		for(int i = 0; i < queries.length; i++) {
			String originalQuery = queries[i];
			String query = originalQuery;
			int matches = 0;
			if(doneQuery.containsKey(query)) {
				answer[i] = doneQuery.get(query);
				continue;
			}
			NavigableSet<String> wordSet;
			if(query.endsWith("?")) {
				wordSet = wordByLen.get(query.length());
				query = query.substring(0, query.indexOf('?'));
			}else {
				wordSet = wordByLenReverse.get(query.length());
				query = query.substring(query.lastIndexOf('?')+1);
				query = new StringBuilder(query).reverse().toString();
			}
			
			if(wordSet==null) {
				answer[i] = 0;
				doneQuery.put(originalQuery, 0);
				continue;
			}
			if(query.length()==0) {
				matches = wordSet.size();
				answer[i] = matches;
				doneQuery.put(originalQuery, matches);
				continue;
			}
            try{
                wordSet = wordSet.tailSet(query, true).headSet(query + "{", false);
                matches = wordSet.size();
			    answer[i] = matches;
			    doneQuery.put(originalQuery, matches);
            }catch(Exception e){
                answer[i] = 0;
				doneQuery.put(originalQuery, 0);
            }
		}
		return answer;
    }
}