// https://programmers.co.kr/learn/courses/30/lessons/12927

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
    public long solution(int n, int[] works) {
    	NavigableMap<Integer, Integer> workByTime = new TreeMap<>((o1, o2) -> o2-o1);
    	int totalTime = 0;
        for(int work : works) {
        	workByTime.compute(work, (k, v)->(v==null) ? 1 : v+1);
        	totalTime += work;
        }
        if(n >= totalTime) return 0;
        while(n > 0) {
        	int time = workByTime.firstKey();
            int cnt = workByTime.get(time);
        	if(n-cnt<0) break;
        	n -= cnt;
        	workByTime.compute(--time, (k,v)->(v==null) ? cnt : v+cnt);
        	workByTime.pollFirstEntry();
        }
        long answer = 0;
        int remaining = n;
        workByTime.compute(workByTime.firstKey(), (k, v)->v-remaining);
        workByTime.compute(workByTime.firstKey()-1, (k, v)->(v==null) ? remaining : v+remaining);
        for(Entry<Integer, Integer> e : workByTime.entrySet()) {
        	answer += e.getKey().longValue() * e.getKey() * e.getValue();
        }
        return answer;
    }
    public static void main(String[] args) {
		int n = 1;
		int[] works = {2,1,2};
		System.out.println(new Solution().solution(n, works));
	}
}