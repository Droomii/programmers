import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
	static Branch root = new Branch();
    public int[] solution(String[] info, String[] query) {
    	for(String inf : info) {
    		root.add(inf);
    	}
    	int[] answer = new int[query.length];
    	for(int i = 0; i < answer.length; i++) {
    		String q = query[i];
    		answer[i] = root.getCnt(q);
    	}
        return answer;
    }
    public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		int[] res = new Solution().solution(info, query);
		System.out.println(Arrays.toString(res));
	}
}

class Branch{
	static String[] langCat = {"java", "python", "cpp"};
	static String[] endCat = {"frontend", "backend"};
	static String[] careerCat = {"junior", "senior"};
	static String[] soulfoodCat = {"pizza", "chicken"};
	static String[][] categories = {langCat, endCat, careerCat, soulfoodCat};
	Map<String, Branch> children;
	NavigableSet<Score> scores;
	public Branch() {
		this(0);
	}
	public int getCnt(String q) {
		String[] split = q.split(" ");
		String[] query = new String[4];
		for(int i = 0; i < 7; i +=2) {
			query[i/2] = split[i];
		}
		int score = Integer.parseInt(split[7]);
		return getCnt(query, 0, score);
		
	}
	private int getCnt(String[] query, int idx, int score) {
		if(idx == 4) {
			return scores.tailSet(new Score(score, -1), true).size();
		}else {
			String q = query[idx];
			if(q.equals("-")) {
				int sum = 0;
				for(Branch b : children.values()) {
					sum += b.getCnt(query, idx+1, score);
				}
				return sum;
			}else {
				return children.get(q).getCnt(query, idx+1, score);
			}
		}
	}
	public Branch(int idx) {
		if(idx < 4) {
			children = new HashMap<>();
			for(String category : categories[idx]) {
				children.put(category, new Branch(idx+1));
			}
		}else {
			scores = new TreeSet<>();
		}
		
		
	}
	public void add(String info) {
		add(info.split(" "), 0);
	}
	public void add(String[] info, int idx) {
		if(idx==4) {
			scores.add(new Score(Integer.parseInt(info[4])));
		}else {
			String s = info[idx++];
			children.get(s).add(info, idx);
		}
	}
	@Override
	public String toString() {
		return "Branch [children=" + children + ", scores=" + scores + "]";
	}
	
}

class Score implements Comparable<Score>{
	
	@Override
	public String toString() {
		return "Score(" + score + ")";
	}

	static int idxCnt = 0;
	int idx;
	int score;
	public Score(int score) {
		this.idx = idxCnt++;
		this.score = score;
	}
	public Score(int score, int idx) {
		this.idx = idx;
		this.score = score;
	}
	
	@Override
	public int compareTo(Score o) {
		if(this.score==o.score) {
			return this.idx-o.idx;
		}
		return this.score - o.score;
	}
	
}