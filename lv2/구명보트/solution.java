// https://programmers.co.kr/learn/courses/30/lessons/42885

import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
    	Arrays.parallelSort(people);
    	int left = 0;
    	int right = people.length-1;
    	
    	int answer = 0;
    	while(left<right) {
    		int lightest = people[left];
    		int heaviest = people[right--];
    		if(lightest+heaviest <= limit) {
    			left++;
    		}
    		answer++;
    	}
    	answer += left==right ? 1 : 0;
        return answer;
    }
}