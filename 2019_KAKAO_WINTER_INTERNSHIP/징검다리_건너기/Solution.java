class Solution {
	public int solution(int[] stones, int k) {
		int min = 200000000;
		
		for(int i = 0; i <= stones.length-k;) {
			int tempMax = stones[i];
			int tempMaxIdx = -1;
			for(int j = i+1; j < i+k; j++) {
				int stone = stones[j];
				if(stone >= tempMax) {
					tempMax = stone;
					tempMaxIdx = j;
				}
			}
			if(tempMaxIdx==-1) {
				i++;
			}else {
				i = tempMaxIdx + 1;
			}
			min = tempMax < min ? tempMax : min;
		}
		return min;
	}
}