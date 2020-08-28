import java.util.Arrays;

class Solution {
    public String solution(int[] numbers, String hand) {
    	return new Hands(hand).pressKeys(numbers);
    }
}

class Hands{
	public boolean isLeftie;
	public int[] leftHandPos;
	public int[] rightHandPos;
	public Hands(String hand) {
		leftHandPos = new int[]{3, 0};
		rightHandPos = new int[]{3, 2};
		this.isLeftie = hand.equals("left");
	}
	public String pressKeys(int[] numbers) {
		int[][] numberCoords = numbersToCoords(numbers);
		StringBuilder sb = new StringBuilder();
		for(int[] coord : numberCoords) {
			if(coord[1]==0) {
				leftHandPos = coord;
				sb.append("L");
			}else if(coord[1]==2) {
				rightHandPos = coord;
				sb.append("R");
			}else {
				int leftDist = Math.abs(leftHandPos[0] - coord[0]) + Math.abs(leftHandPos[1] - coord[1]);
				int rightDist = Math.abs(rightHandPos[0] - coord[0]) + Math.abs(rightHandPos[1] - coord[1]);
				if(leftDist>rightDist) {
					rightHandPos = coord;
					sb.append("R");
				}else if(leftDist<rightDist) {
					leftHandPos = coord;
					sb.append("L");
				}else if(isLeftie) {
					leftHandPos = coord;
					sb.append("L");
				}else {
					rightHandPos = coord;
					sb.append("R");
				}
			}
		}
		return sb.toString();
	}
	public int[][] numbersToCoords(int[] numbers){
		return Arrays.stream(numbers).mapToObj(a ->{
			if(a==0) {
				return new int[] {3,1};
			}else {
				return new int[] {(a-1)/3, (a-1)%3};
			}
		}).toArray(int[][]::new);
	}
	
}