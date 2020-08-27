import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
    	SortedSet<FileName> fileSet = Arrays.stream(files).map(FileName::new).collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
    	System.out.println(fileSet);
    	
        String[] answer = fileSet.stream().map(o -> o.toString()).toArray(String[]::new);
        return answer;
    }
}

class FileName implements Comparable<FileName>{
	public static int idxCounter;
	public int idx;
	public String head;
	public String number;
	public String tail;
	public String originalName;
	
	public FileName(String file) {
		this.originalName = file;
		this.idx = idxCounter++;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(file);
		m.find();
		this.number = m.group();
		int tailIdx = m.end();
		int numberIdx = m.start();
		this.head = file.substring(0, numberIdx).toLowerCase();
		this.tail = file.substring(tailIdx).toLowerCase();
	}

	@Override
	public int compareTo(FileName o) {
		int compareHead = this.head.compareTo(o.head);
		if(compareHead==0) {
			int compareNumber = Integer.compare(this.getNumber(), o.getNumber());
			if(compareNumber==0) {
				return this.idx - o.idx;
			}
			return compareNumber;
		}
		return compareHead;
	}
	private int getNumber() {
		return Integer.parseInt(this.number);
	}
	@Override
	public String toString() {
		return this.originalName;
	}
}