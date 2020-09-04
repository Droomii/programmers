// https://programmers.co.kr/learn/courses/30/lessons/42583

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
    	Queue<Integer> bridge = new ArrayDeque<Integer>();
    	Queue<Integer> trucks = Arrays.stream(truck_weights).collect(ArrayDeque::new, ArrayDeque::add, ArrayDeque::addAll);
    	IntStream.range(0, bridge_length).forEach(a -> bridge.add(0));
    	int time = 1;
    	int currWeight = 0;
    	for(;;time++) {
    		currWeight -= bridge.poll();
			if(!trucks.isEmpty() && (trucks.peek()+currWeight <= weight)) {
				currWeight += trucks.peek();
				bridge.add(trucks.poll());
			}else
				bridge.add(0);
    		
    		if(currWeight==0) break;
    	}
        return time;
    }
}