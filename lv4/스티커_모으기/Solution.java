// https://programmers.co.kr/learn/courses/30/lessons/12971

class Solution {
    public int solution(int sticker[]) {
        if(sticker.length==1) return sticker[0];
        if(sticker.length==2) return Integer.max(sticker[0], sticker[1]);
    	int[] pickFirst = new int[sticker.length];
    	int[] pickLast = new int[sticker.length];
    	pickFirst[0] = sticker[0];
    	pickFirst[1] = Integer.max(sticker[0], sticker[1]);
    	pickLast[0] = 0;
    	pickLast[1] = sticker[1];
    	for(int i = 2; i < sticker.length; i++) {
    		if(i < sticker.length-1) {
    			pickFirst[i] = Integer.max(pickFirst[i-1], sticker[i] + pickFirst[i-2]);
    		}
    		pickLast[i] = Integer.max(pickLast[i-1], sticker[i] + pickLast[i-2]);
    	}
        return Integer.max(pickFirst[sticker.length-2], pickLast[sticker.length-1]);
    }
}