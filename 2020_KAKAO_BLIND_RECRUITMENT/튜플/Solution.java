import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String s) {
    	
    	String[] split = s.split(",\\{");
    	List<List<Integer>> sList = new ArrayList<>();
    	for(String ss : split) {
    		String removeBracket = ss.replaceAll("[\\{\\}]", "");
    		String[] numbers = removeBracket.split(",");
    		sList.add(Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList()));
    	}
    	Collections.sort(sList, new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.size() - o2.size();
			}
		});
    	
    	Set<Integer> numberSet = new LinkedHashSet<Integer>();
    	for(List<Integer> e : sList) {
    		numberSet.addAll(e);
    	}
    	
        int[] answer = numberSet.stream().mapToInt(a -> a).toArray();
        return answer;
    }
}