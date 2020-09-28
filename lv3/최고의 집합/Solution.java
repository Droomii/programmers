// https://programmers.co.kr/learn/courses/30/lessons/12938

import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
    	if(n>s) return new int[] {-1};
    	
    	int[] answer = new int[n];
    	int div = s / n;
    	int mod = s % n;
    	Arrays.fill(answer, div);
    	for(int i = n-1; i > n-1-mod; i--) {
    		answer[i]++;
    	}
        return answer;
    }
}