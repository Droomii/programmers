// https://programmers.co.kr/learn/courses/30/lessons/68644

import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
    public int[] solution(int[] numbers) {
    	SortedSet<Integer> sumSet = new TreeSet<>();
    	
    	for(int i = 0; i < numbers.length; i++) {
    		for(int k = i + 1; k < numbers.length; k++) {
    			sumSet.add(numbers[i] + numbers[k]);
    		}
    	}
    	int[] answer = sumSet.stream().mapToInt(a -> a).toArray();
        return answer;
    }
    
}