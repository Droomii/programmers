// https://programmers.co.kr/learn/courses/30/lessons/42746

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
    	Queue<String> numQ = new PriorityQueue<>((a, b) -> {
    		return (b + a).compareTo(a+b);
    	});
    	for(int i = 0; i < numbers.length; i++) {
    		numQ.add(Integer.toString(numbers[i]));
    	}
    	StringBuilder sb = new StringBuilder();
    	while(!numQ.isEmpty()) {
    		sb.append(numQ.poll());
    	}
    	String answer = sb.toString().replaceFirst("^0+", "");
    	if(answer.isEmpty()) return "0";
        return answer;
    }
}