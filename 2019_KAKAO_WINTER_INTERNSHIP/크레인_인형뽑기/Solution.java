import java.util.EmptyStackException;
import java.util.Stack;

class Solution {
	public int solution(int[][] board, int[] moves) {

		Stack<Integer>[] boardStacks = new Stack[board.length];
		for (int i = 0; i < boardStacks.length; i++) {
			Stack<Integer> boardStack = new Stack<Integer>();
			for (int k = boardStacks.length - 1; k > -1; k--) {
				if (board[k][i] == 0)
					break;
				boardStack.push(board[k][i]);
			}
			boardStacks[i] = boardStack;
		}
		Stack<Integer> picked = new Stack<Integer>();
		int popped = 0;
		for (int move : moves) {
			int top = 0;
			try {
				top = picked.peek();
			} catch (EmptyStackException e) {
			}
			int pick = 0;
			try {
				pick = boardStacks[move - 1].pop();

			} catch (EmptyStackException e) {
				continue;
			}
			if (top == pick) {
				popped += 2;
				picked.pop();
				
			}else {
				picked.push(pick);
			}
		}
		return popped;
	}
}