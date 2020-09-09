// https://programmers.co.kr/learn/courses/30/lessons/12907

import java.util.Arrays;

class Solution {
	static final int MOD = 1000000007;
    public int solution(int n, int[] money) {
    	Arrays.sort(money);
    	int[] options = new int[n+1];
    	options[0] = 1;
    	for(int i = 0; i < money.length; i++) {
    		for(int j = money[i]; j <= n; j++) {
    			options[j] += options[j-money[i]];
    		}
    	}
    	return options[n] % MOD;
    }
}