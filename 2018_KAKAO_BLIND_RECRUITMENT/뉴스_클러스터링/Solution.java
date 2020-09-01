// https://programmers.co.kr/learn/courses/30/lessons/17677

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(String str1, String str2) {
    	str1 = str1.toLowerCase().replaceAll("[^a-z]", "-");
    	str2 = str2.toLowerCase().replaceAll("[^a-z]", "-");
    	Map<String, Integer> str1Map = new HashMap<>();
    	Map<String, Integer> str2Map = new HashMap<>();
    	
    	for(int i = 0; i < str1.length()-1; i++) {
    		if(!str1.substring(i, i+2).contains("-"))
    			str1Map.compute(str1.substring(i, i+2), (k, v) -> (v==null) ? 1 : v+1);
    	}
    	for(int i = 0; i < str2.length()-1; i++) {
    		if(!str2.substring(i, i+2).contains("-"))
    			str2Map.compute(str2.substring(i, i+2), (k, v) -> (v==null) ? 1 : v+1);
    	}
    	Set<String> union = new HashSet<>(str1Map.keySet());
    	union.addAll(str2Map.keySet());
    	double unionCnt = 0;
    	for(String u : union) {
    		unionCnt += Integer.max(str1Map.getOrDefault(u, 0), str2Map.getOrDefault(u, 0));
    	}
    	if(unionCnt==0) {
    		return 65536;
    	}
    	Set<String> intersect = new HashSet<>(str1Map.keySet());
    	intersect.retainAll(str2Map.keySet());
    	double intersectCnt = 0;
    	for(String i : intersect) {
    		intersectCnt += Integer.min(str1Map.get(i), str2Map.get(i));
    	}
    	double jaccard = intersectCnt / unionCnt;
    	jaccard *= 65536;
        return (int)jaccard; 
    }
    public static void main(String[] args) {
		String str1 = "FRANCE";
		String str2 = "french";
		
		System.out.println(new Solution().solution(str1, str2));
	}
}