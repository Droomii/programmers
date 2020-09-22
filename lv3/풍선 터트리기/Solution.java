// https://programmers.co.kr/learn/courses/30/lessons/68646

class Solution {
    public int solution(int[] a) {
    	if(a.length==1) return 1;
    	if(a.length==2) return 2;
    	int answer = 2;
    	int leftMin = a[0];
    	int rightMin = a[a.length-1];
    	int leftIdx = 0;
    	int rightIdx = a.length-1;
    	while(rightIdx-leftIdx > 1) {
    		if(leftMin > rightMin) {
    			if(a[++leftIdx] < leftMin) {
    				answer++;
    				leftMin = a[leftIdx];
    			}else if(a[leftIdx] < rightMin) {
    				answer++;
    			}
    		}else {
    			if(a[--rightIdx] < rightMin) {
    				answer++;
    				rightMin = a[rightIdx];
    			}else if(a[rightIdx] < leftMin) {
    				answer++;
    			}
    		}
    	}
    	return answer;
    }
}