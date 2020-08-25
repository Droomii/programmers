import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
	
	public static SortedSet<Prop> propSet = new TreeSet<Prop>();
	public int[][] solution(int n, int[][] build_frame) {
		// 0은 기둥, 1은 보
		// 0은 삭제, 1은 설치
		for(int[] op : build_frame) {
			Prop p = new Prop(op[0], op[1], op[2]);
			if(op[3]==1) {
				propSet.add(p);
				if(!allOK(n)) {
					propSet.remove(p);
				}
			}else {
				propSet.remove(p);
				if(!allOK(n)) {
					propSet.add(p);
				}
			}
		}
		int[][] answer = new int[propSet.size()][3];
		int i = 0;
		for(Prop p : propSet) {
			answer[i] = p.toArray();
			i++;
		}
		
		return answer;
		
	}
	
	public static boolean allOK(int n) {
		
		for(Prop p : propSet) {
			boolean OK = false;
			if(p.type==0) {
				OK |= p.y==0;
				OK |= propSet.contains(new Prop(p.x, p.y-1, 0));
				OK |= propSet.contains(new Prop(p.x, p.y, 1));
				OK |= propSet.contains(new Prop(p.x-1, p.y, 1));
			}else {
				OK |= propSet.contains(new Prop(p.x, p.y-1, 0));
				OK |= propSet.contains(new Prop(p.x+1, p.y-1, 0));
				OK |= propSet.contains(new Prop(p.x+1, p.y, 1)) && propSet.contains(new Prop(p.x-1, p.y, 1)); 
			}
			if(!OK) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		int n = 5;
		Solution sol = new Solution();
		int[][] answer = sol.solution(n, build_frame);
		for(int[] a : answer) {
			for(int b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
	}
}

class Prop implements Comparable<Prop>{
	
	
	public int x;
	public int y;
	public int type;

	public Prop(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int[] toArray() {
		return new int[] { x, y, type };
	}

	@Override
	public String toString() {
		return String.format("[%d, %d, %d]", x, y, type);
	}

	@Override
	public int compareTo(Prop o) {
		if(this.x==o.x) {
			if(this.y==o.y) {
				return this.type-o.type;
			}
			return this.y-o.y;
		}
		return this.x - o.x;
	}
	@Override
	public boolean equals(Object obj) {
		Prop o = (Prop)obj;
		boolean equal = this.x == o.x;
		equal &= this.y == o.y;
		equal &= this.type == o.type;
		return equal;
	}
}