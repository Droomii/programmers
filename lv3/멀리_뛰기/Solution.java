// https://programmers.co.kr/learn/courses/30/lessons/12914

class Solution {
    public long solution(int n) {
    	int[] fib = {0, 0, 1};
    	for(int i = 0; i < n; i++) {
    		fib[0] = fib[1];
    		fib[1] = fib[2];
    		fib[2] = (fib[0]+fib[1]) % 1234567;
    	}
        return fib[2];
    }
}