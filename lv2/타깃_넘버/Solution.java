// https://programmers.co.kr/learn/courses/30/lessons/43165

class Solution {
	
	public static int cnt = 0;
	public static int[] staticNumber;
    public int solution(int[] numbers, int target) {
    	staticNumber = numbers;
    	
    	dfs(target, 0, 0);
        return cnt;
    }
    public void dfs(int target, int idx, int current) {
    	if(idx==staticNumber.length) {
    		if(current==target) {
    			cnt++;
    		}
    		return;
    	}
    	dfs(target, idx+1, current+staticNumber[idx]);
    	dfs(target, idx+1, current-staticNumber[idx]);
    }
}