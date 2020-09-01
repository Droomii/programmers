// https://programmers.co.kr/learn/courses/30/lessons/42626

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[] scoville, int K) {
    	Queue<Integer> q = Arrays.stream(scoville).collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
    	
    	int cnt = 0;
    	while(q.size()>1) {
    		int a = q.poll();
    		if(a>=K) {
    			return cnt;
    		}
    		int b = q.poll();
    		int c = a + b * 2;
    		q.add(c);
    		cnt++;
    	}
    	if(q.poll()>=K) {
    		return cnt;
    	}
    	return -1;
    	
    }
}