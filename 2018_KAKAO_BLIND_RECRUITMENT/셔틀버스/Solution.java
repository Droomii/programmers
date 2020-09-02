// https://programmers.co.kr/learn/courses/30/lessons/17678

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class Solution {
	public static final int NINE = 540;

	public String solution(int n, int t, int m, String[] timetable) {
		// n : 횟수
		// t : 시간 간격
		// m : 수용인원
		
		// 대기 직원 도착시간 int로 변환
		Queue<Integer> waitLine = Arrays.stream(timetable).mapToInt(a -> {
			String[] split = a.split(":");
			int hour = Integer.parseInt(split[0]);
			int min = Integer.parseInt(split[1]);
			int time = hour * 60 + min;
			return time;
		}).collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
		int currentTime = NINE;
		
		int latestTime = 0;
		
		// 버스 배차 횟수에 따라
		for(int i = n; i > 0; i--) {
			
			Stack<Integer> bus = new Stack<Integer>();
			// 수용 인원수에 따라
			for(int k = m; k > 0; k--) {
				
				try {
					// 버스보다 일찍 도착한 사람 태우기
					if(waitLine.peek()<=currentTime) {
						bus.push(waitLine.poll());
					}
				// 대기줄 없는 경우
				}catch(NullPointerException e){
					latestTime = currentTime;
				}
				// 막차일때
				if(i==1) {
					// 자리 남았을때
					if(bus.size()<m) {
						latestTime = currentTime;
					// 자리 없을때
					}else {
						// 마지막 탑승 직원보다 1분 일찍 도착
						latestTime = bus.peek()-1;
					}
				}
			}
			// 다음 버스 도착시간
			currentTime += t;
		}
		// int 시간은 String으로 변환
		String answer = String.format("%02d:%02d", latestTime/60, latestTime%60);
		return answer;
	}

	public static void main(String[] args) {
		int n = 2;
		int t = 10;
		int m = 2;
		String[] timetable = { "09:10", "09:09", "08:00" };
		System.out.println(new Solution().solution(n, t, m, timetable));
	}
}