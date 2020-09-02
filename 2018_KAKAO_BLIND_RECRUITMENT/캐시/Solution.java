// https://programmers.co.kr/learn/courses/30/lessons/17680

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int cacheSize, String[] cities) {
    	if(cacheSize==0) {
    		return cities.length * 5;
    	}
    	int totalTime = 0;
    	int clock = 0;
    	List<Cache> memory = new ArrayList<>();
    	for(String city : cities) {
    		// 캐시에 있는 경우
    		int cacheIdx = memory.indexOf(new Cache(city, clock)); 
    		if(cacheIdx !=-1) {
    			memory.get(cacheIdx).lastAccess = clock;
    			memory.sort(null);
    			totalTime += 1;
    		// 캐시에 없는 경우
    		}else {
    			// 실행 시간 더함
    			totalTime += 5;
    			// 캐시 꽉 찬 경우
    			if(memory.size()==cacheSize) {
    				memory.remove(0);
    				memory.add(new Cache(city, clock));
    			// 공간 있는 경우
    			}else {
    				memory.add(new Cache(city, clock));
    			}
    		}
    		clock++;
    	}
        return totalTime;
    }
}

class Cache implements Comparable<Cache>{
	String cityName;
	int lastAccess;
	int cachedTime;
	
	@Override
	public String toString() {
		return "Cache [city=" + cityName + ", lastAccess=" + lastAccess + ", cachedTime=" + cachedTime + "]";
	}

	public Cache(String cityName, int cachedTime) {
		this.cityName = cityName;
		this.cachedTime = cachedTime;
		this.lastAccess = cachedTime;
	}
	
	@Override
	public int compareTo(Cache o) {
		if(this.lastAccess==o.lastAccess) {
			return this.cachedTime - o.cachedTime;
		}
		return this.lastAccess - o.lastAccess;
	}
	
	@Override
	public boolean equals(Object obj) {
		Cache query = (Cache)obj;
		// TODO Auto-generated method stub
		return this.cityName.equalsIgnoreCase(query.cityName);
	}
	
}
