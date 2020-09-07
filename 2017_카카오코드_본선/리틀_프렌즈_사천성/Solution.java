// https://programmers.co.kr/learn/courses/30/lessons/1836

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
    public String solution(int m, int n, String[] board) {
    	// 외벽을 포함한 그리드 만들기
    	char[][] grid = new char[m+2][n+2];
    	// 처음 타일 넣을 맵
    	SortedMap<Character, Tile> tileMap = new TreeMap<>();
    	// 그리드 채우기
    	for(int i = 0; i < grid.length; i++) {
    		for(int k = 0; k < grid[0].length; k++) {
    			if(i==0 || i==grid.length-1 || k==0 || k==grid[0].length-1) {
    				grid[i][k] = '*';
    			}else {
    				char c = board[i-1].charAt(k-1);
    				grid[i][k] = c;
    				// 알파벳인 경우
    				if(Character.isAlphabetic(c)) {
    					// 이미 타일 객채가 생성된 경우 된 경우 타일 객체 안에 짝 타일 생성
    					if(tileMap.containsKey(c)) {
    						tileMap.get(c).makePair(i, k);
    					}else {
    						tileMap.put(c, new Tile(c, i, k, grid));
    					}
    				}
    			}
    		}
    	}
    	StringBuilder answerSb = new StringBuilder();
    	Iterator<Character> it = tileMap.keySet().iterator();
    	while(it.hasNext()) {

    		Tile t = tileMap.get(it.next());
    		t.reach();
    		if(t.intersects()) {
    			grid[t.row][t.col] = '.';
    			grid[t.pairTile.row][t.pairTile.col] = '.';
    			answerSb.append(t.tileChar);
    			it.remove();
    			if(tileMap.isEmpty()) {
    				return answerSb.toString();
    			}
    			it = tileMap.keySet().iterator();
    		}
    	}
        return "IMPOSSIBLE";
    }
}

class Tile{
	public char[][] grid;
	public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public char tileChar;
	public int col;
	public int row;
	public Tile pairTile;
	public List<Coord> crossCoords;
	public Coord[] lastCoords;
	public Tile(char tileChar, int row, int col, char[][] grid) {
		this.grid = grid;
		this.tileChar = tileChar;
		this.col = col;
		this.row = row;
		this.crossCoords = new ArrayList<>();
		this.crossCoords.add(new Coord(row, col, 0));
		this.lastCoords = new Coord[4];
		IntStream.range(0, 4).forEach(i -> {
			lastCoords[i] = new Coord(row+dir[i][0], col+dir[i][1], i);
		});
		
	}
	public void makePair(int row, int col) {
		this.pairTile = new Tile(this.tileChar, row, col, grid);
	}
	public void reach() {
		for(Coord c : lastCoords) {
			reach(c, tileChar);
		}
		if(pairTile!=null) {
			this.pairTile.reach();
		}
	}
	private void reach(Coord c, char tileChar) {
		if(grid[c.row][c.col]=='.') {
			this.crossCoords.add(c);
			Coord next = c.reach();
			this.lastCoords[c.dir] = next;
			reach(next, tileChar);
		}
		else if(grid[c.row][c.col]==tileChar) {
			this.crossCoords.add(c);
		}
	}
	public boolean intersects() {
		return this.crossCoords.removeAll(this.pairTile.crossCoords);
	}
	@Override
	public String toString() {
		return "Tile [tileChar=" + tileChar + "]";
	}
	
	
}

class Coord{
	public int row;
	public int col;
	public int dir;
	public static int[][] movement = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public Coord(int row, int col, int dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	public Coord reach() {
		return new Coord(row+movement[dir][0], col+movement[dir][1], dir);
	}
	
	@Override
	public boolean equals(Object obj) {
		Coord o = (Coord)obj;
		return this.row == o.row && this.col == o.col;
	}
	@Override
	public String toString() {
		return "Coord [" + row + ", " + col + "]";
	}
	
	
}