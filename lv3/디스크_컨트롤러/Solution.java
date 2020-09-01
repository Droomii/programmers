// https://programmers.co.kr/learn/courses/30/lessons/42627

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[][] jobs) {
    	Queue<Job> jobQ = new PriorityQueue<Job>();
    	for(int[] job : jobs) {
    		jobQ.add(new Job(job[0], job[1]));
    	}
    	
    	Queue<Job> waitQ = new PriorityQueue<Job>((a, b)->{
    		return a.bt - b.bt;
    	});
    	
    	List<Job> completed = new ArrayList<>();
    	int time = 0;
    	Job currentJob = null;
    	
    	while(jobs.length!=completed.size()) {
    		if(!waitQ.isEmpty()) {
    			currentJob = waitQ.poll();
    		}else {
    			currentJob = jobQ.poll();
    			time = currentJob.at;
    		}
    		currentJob.calcTat(time);
    		time += currentJob.bt;
    		completed.add(currentJob);
    		try {
    			while(jobQ.peek().at < time) {
        			waitQ.add(jobQ.poll());
        		}
    		}catch(Exception e) {}
    		
    	}
    	return (int)completed.stream().mapToInt(a -> a.tat).average().getAsDouble();
    }
    public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		System.out.println(new Solution().solution(jobs));
	}
}

class Job implements Comparable<Job>{
	public int at;
	public int bt;
	public int tat;
	
	public Job(int at, int bt) {
		super();
		this.at = at;
		this.bt = bt;
	}

	public void calcTat(int time) {
		this.tat = time - at + bt;
	}

	@Override
	public int compareTo(Job o) {
		if(this.at - o.at==0)
			return this.bt - o.bt;
		return this.at - o.at;
	}

	@Override
	public String toString() {
		return "Job [at=" + at + ", bt=" + bt + ", tat=" + tat + "]";
	}
}