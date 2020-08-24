import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] solution(String[] operations) {
    	Deque<Integer> dq = new LinkedList<Integer>();
    	for(String op : operations) {
    		String[] opSplit = op.split(" ");
    		if(opSplit[0].equals("I")) {
    			dq.add(Integer.parseInt(opSplit[1]));
    			Collections.sort((List<Integer>)dq);
    		}else {
    			if(opSplit[1].equals("1")) {
    				dq.pollLast();
    			}else {
    				dq.pollFirst();
    			}
    		}
    	}
    	
        int[] answer = {0,0};
        answer[0] = dq.peekLast()==null ? 0 : dq.pollLast();
        answer[1] = dq.peekFirst()==null ? 0 : dq.pollFirst(); 
         
        return answer;
    }
}