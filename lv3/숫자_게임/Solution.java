// https://programmers.co.kr/learn/courses/30/lessons/12987

import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
    public int solution(int[] A, int[] B) {
    	NavigableMap<Integer, Integer> cardCnt = new TreeMap<>();
    	int winCnt = 0;
    	for(int card : B) {
    		cardCnt.compute(card, (k, v)->(v==null) ? 1 : v+1);
    	}
    	for(int opponent : A) {
    		int higher;
    		try {
    			higher = cardCnt.higherKey(opponent);
    			cardCnt.compute(higher, (k, v)->(v-1==0) ? null : v-1);
    			winCnt++;
    		}catch(NullPointerException e) {
    			cardCnt.pollFirstEntry();
    		}
    		
    	}
        return winCnt;
    }
}