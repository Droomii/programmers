// https://programmers.co.kr/learn/courses/30/lessons/12936

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
    	List<Integer> remaining = new ArrayList<>();
    	int choices = n-1;
    	long factorial = 1;
    	for(int i = 1; i < n; i++) {
    		remaining.add(i);
    		factorial *= i;
    	}
    	remaining.add(n);
    	k -= 1;
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
        	answer[i] = remaining.remove((int)(k / factorial));
        	k %= factorial;
        	try {
        		factorial /= choices--;
        	}catch(ArithmeticException e) {
        		break;
        	}
        	
        }
        return answer;
    }
}