// https://programmers.co.kr/learn/courses/30/lessons/68646

class Solution {
    public int solution(int[] a) {
    	if(a.length==1) return 1;
    	if(a.length==2) return 2;
    	
    	int answer = 1;
    	int smallestIdx = 0;
    	int smallest = 1000000001;
    	for(int i = 0; i < a.length; i++) {
    		smallest = Integer.min(smallest, a[i]);
    		if(smallest==a[i]) {
    			smallestIdx = i;
    		}
    		if(smallest==-1000000000) break;
    	}
    	
    	int min = 1000000001;
    	for(int i = 0; i < smallestIdx; i++) {
    		if(a[i] < min) {
    			answer++;
    			min = a[i]; 
    		}
    	}
    	min = 1000000001;
    	for(int i = a.length-1; i > smallestIdx; i--) {
    		if(a[i] < min) {
    			answer++;
    			min = a[i]; 
    		}
    	}
    	return answer;
    }
}