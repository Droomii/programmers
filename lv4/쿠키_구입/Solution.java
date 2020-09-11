// https://programmers.co.kr/learn/courses/30/lessons/49995

class Solution {
	public int solution(int[] cookie) {

		int max = 0;
		if (cookie.length == 1)
			return 0;
		if (cookie.length == 2)
			return cookie[0] == cookie[1] ? cookie[0] : 0;

		for(int i = 0; i < cookie.length-1; i++) {
			int leftIdx = i;
			int rightIdx = i+1;
			int leftSum = cookie[leftIdx];
			int rightSum = cookie[rightIdx];
			
			while(true) {
				if(rightSum==leftSum)
					max = Integer.max(max, rightSum);
				if(leftIdx > 0 && leftSum <= rightSum) {
					leftSum += cookie[--leftIdx];
				}else if(rightIdx < cookie.length-1 && leftSum >= rightSum) {
					rightSum += cookie[++rightIdx];
				}else break;
			}
		}
		return max;
	}
}