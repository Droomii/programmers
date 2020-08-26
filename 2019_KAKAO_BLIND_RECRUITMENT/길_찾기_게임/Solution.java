import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

	public static List<Node> nodes;
	public static List<Integer> preorder = new ArrayList<>();
	public static List<Integer> postorder = new ArrayList<>();

	public static Comparator<Node> sortByX = new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.x - o2.x;
		}
	};

	public static Comparator<Node> sortByY = new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.y == o2.y) {
				return o1.x - o2.x;
			}
			return o2.y - o1.y;
		}
	};

	public int[][] solution(int[][] nodeinfo) {
		nodes = Arrays.stream(nodeinfo).map(o -> new Node(o[0], o[1], null)).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		List<Node> nodeByY = new ArrayList<Node>(nodes);
		nodeByY.sort(sortByY);
		Node root = nodeByY.get(0);
		List<Node> nodeByX = new ArrayList<Node>(nodes);
		nodeByX.sort(sortByX);
		connectNodes(nodeByX, nodeByY);
		preorderTraversal(root);
		postorderTraversal(root);
		int[][] answer = new int[2][nodeinfo.length];
		answer[0] = preorder.stream().mapToInt(a -> a).toArray();
		answer[1] = postorder.stream().mapToInt(a -> a).toArray();
		
		return answer;
	}
	
	public static void preorderTraversal(Node n) {
		preorder.add(n.no);
		if(n.left!=null) {
			preorderTraversal(n.left);
		}
		if(n.right!=null) {
			preorderTraversal(n.right);
		}
	}
	
	public static void postorderTraversal(Node n) {
		if(n.left!=null) {
			postorderTraversal(n.left);
		}
		if(n.right!=null) {
			postorderTraversal(n.right);
		}
		postorder.add(n.no);
	}
	
	public static void connectNodes(List<Node> nodeByX, List<Node> nodeByY) {
		if (nodeByX.size() == 1) {
			return;
		}
		Node parent = nodeByY.get(0);
		int childLevel = nodeByY.get(1).y;
		for (Node child : nodeByY.subList(1, nodeByY.size())) {
			List<Node> subNodeByX;
			if (child.y != childLevel) {
				break;
			}
			child.parent = parent;
			if (parent.x > child.x) {
				parent.left = child;
				subNodeByX = nodeByX.subList(0, nodeByX.indexOf(parent));
			} else {
				parent.right = child;
				subNodeByX = nodeByX.subList(nodeByX.indexOf(parent) + 1, nodeByX.size());
			}
			List<Node> subNodeByY = subNodeByX.stream().sorted(sortByY).collect(Collectors.toList());
			connectNodes(subNodeByX, subNodeByY);
		}

	}
}

class Tree {
	public Node root;

	public Tree(Node root) {
		this.root = root;
	}
}

class Node {
	public static int counter = 1;
	public int no;
	public int x;
	public int y;
	public Node parent;
	public Node left;
	public Node right;

	public Node(int x, int y, Node parent) {
		this.no = counter++;
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Node [no=" + no + ", x=" + x + ", y=" + y + ", parent=" + (parent == null ? "x"
				: parent.no) + ", left=" + (left == null ? "x"
						: left.no) + ", right=" + (right == null ? "x"
								: right.no) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Node o = (Node) obj;
		return this.no == o.no;
	}

}