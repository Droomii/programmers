// https://programmers.co.kr/learn/courses/30/lessons/42884

import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[][] routes) {
    	if(routes.length==1) {
    		return 1;
    	}
    	Queue<Car> q = new PriorityQueue<Car>();
    	for(int[] route : routes) {
    		q.add(new Car(route[0], route[1]));
    	}
    	int cnt = 1;
    	Car currentCar = q.poll();
    	int start = currentCar.start;
    	int end = currentCar.end;
    	while(!q.isEmpty()) {
    		currentCar = q.poll();
    		start = start < currentCar.start ? currentCar.start : start;
    		end = end > currentCar.end ? currentCar.end : end;
    		if(start>end) {
    			cnt++;
    			start = currentCar.start;
    			end = currentCar.end;
    		}
    	}
    	return cnt;
    }
}

class Car implements Comparable<Car>{
	public int start;
	public int end;
	
	public Car(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Car o) {
		return this.start - o.start;
	}
	
}