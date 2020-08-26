import java.util.HashMap;
import java.util.Map;

class Solution {
	Map<Long, Long> occupied = new HashMap<Long, Long>();
	
    public long[] solution(long k, long[] room_number) {
    	
		int n = room_number.length;
		
		long[] answer = new long[n];
		
		for(int i = 0; i < n; i++) {
			answer[i] = getEmptyRoom(room_number[i]);
		}
		
		return answer;
		
    }
    
    public long getEmptyRoom(long room_num) {
    	if(!occupied.containsKey(room_num)) {
    		occupied.put(room_num, room_num+1);
    		return room_num;
    	}
    	
    	long nextRoom = occupied.get(room_num);
    	long nextEmptyRoom = getEmptyRoom(nextRoom);
    	return nextEmptyRoom;
    }

}