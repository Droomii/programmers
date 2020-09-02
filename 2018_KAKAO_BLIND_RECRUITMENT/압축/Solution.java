// https://programmers.co.kr/learn/courses/30/lessons/17684

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static int[] solution(String msg) {
    	// 압축 사전
    	Map<String, Integer> dict = new HashMap<String, Integer>();
    	
    	// 새 압축 문자열 인덱스
    	int newDictIdx = 27;
    	
    	// 문자 모아둘 스트링빌더
    	StringBuilder sb = new StringBuilder();
    	
    	// 단어 압축된 리스트
    	List<Integer> compressed = new ArrayList<>();
    	int pointer = 0;
		while(pointer < msg.length()) {
			String currentChar = String.valueOf(msg.charAt(pointer++));
			sb.append(currentChar);
			String currentStr = sb.toString();
			
			if(!dict.containsKey(currentStr)) {
				if(currentStr.length()==1) {
					dict.put(currentChar, currentChar.charAt(0)-'A'+1);
					
				}else {
					dict.put(currentStr, newDictIdx++);
        			compressed.add(dict.get(currentStr.substring(0, currentStr.length()-1)));
				}
				pointer--;
				sb = new StringBuilder();
    			
    		}
		}
		if(!sb.toString().isEmpty())
			compressed.add(dict.get(sb.toString()));

    	
        return compressed.stream().mapToInt(a -> a).toArray();
    }
}