import java.util.LinkedList;
import java.util.Queue;

;class TreeNode{
			public int info;
			public TreeNode left, right;
				
			public TreeNode(int x){
				info = x;
				left = right = null;
			}	
		}
		



class SortedTree{
	public final static int SIZE_INPUT = 8;
	TreeNode root;
	
	/**
	 * Receives a sorted sequence of integers to build 
	 * a binary tree. Given any node nd in this binary tree,
	 * let its subtrees be denoted t1 and t2. The number of 
	 * nodes in t1 and t2 differ by no more than one.
	 */
	
	public SortedTree(int[] input){
		root = null;
		if(!verifyInput(input)){ //if input is not sorted return
			return;
		}else{
			root = buildTree(input, 0, input.length-1);
		}
	}
	
	/**
	 * Builds a binary using the objects from input[start]
	 * to input[end].
	 * 
	 * See the constructor for the requirements for this binary tree 
	 */
	
	private TreeNode buildTree(int[] input, int start, int end) {
		
		int t1 = start;
		int t2 = end; 
		int middle = (t1 + t2) / 2;
		
		/*
		 *  t1 is default to 0. if input array is empty
		 *  then t2 would be 0, this will cause the method
		 *  to return null.
		 */
		if (t1 > t2){  
			return null;
		}
		
		TreeNode nd = new TreeNode(middle);

		nd.left = buildTree(input, start, middle - 1);
		nd.right = buildTree(input, middle + 1, end);
		return nd; 	
	}

	
	private boolean verifyInput(int [] input){
		if(input == null){
			return false;
		}
		
		for(int i=0; i < input.length - 1; i++){
			if(input[i] >= input[i+1]){
				return false;
			}
		}		
		return true;
	}
	
	public int depth(){
		return _depth(root);
	}
	
	/*	Depth node finds the deepest level of a tree. This
	 * 	Method traverses the tree using recursion and adds
	 * 	1 to the counter if its able to traverse to the next
	 * 	level. Once at the end, it compares both left and right
	 * 	counters and which ever holds the biggest number is returned.
	 */
	
	public int _depth(TreeNode root){
		
		int left_Counter = 0; //left counter
		int Right_Counter = 0; //right counter
		
		if(root == null) {
			return -1;
		}
		
		if(root.left == null && root.right == null) {
			return 0;
		}
		
		if(root.left != null) {	
			left_Counter = 1 + _depth(root.left);
		}
		if(root.right != null) {
			Right_Counter = 1 + _depth(root.right);
		}
		
		return (left_Counter > Right_Counter) 
					? left_Counter : Right_Counter;
	}
	
	public void traverse(){
		traverseNodes(root);
	}
	
	private void traverseNodes(TreeNode root){
		if(root == null) {
			return;
		}

		if(root.left != null) {
			traverseNodes(root.left);
		}
		
		System.out.print(root.info + " ");
		
		if(root.right != null) { 
		traverseNodes(root.right);
		}
	}
	
	public void level() {
		levelOrder(root);
	}
	
	private void levelOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		
		while(q!=null) {
			TreeNode current = q.peek();
			System.out.print(current.info + " ");
			
			if( current.left != null) {
				q.add(current.left);
			}
			if(current.right != null) {
				q.add(current.right);
			}
			q.remove();
		}
	}
	
	public void tless(int k) {
		traverseless(root, k);
	}
	
	private void traverseless(TreeNode nd, int k) {
		if (root == null) {
			return;
		}
		
		if(nd.left != null) {
			traverseless(nd.left, k);
		}
		if(nd.info < k) {
			System.out.print(nd.info + " ");
		}
		if(nd.right != null) {
			traverseless(nd.right,k);
		}
	}
	
	
	//see page 351 for bubble sort
	
}

class Lab3 {
	
		public static void main(String[] args) {
			int[] input = new int[SortedTree.SIZE_INPUT];
			for(int i = 0; i < SortedTree.SIZE_INPUT; i++){			
				input[i] = i;
			}
	
			//1 - create the binary search tree given the sorted input 
			SortedTree st = new SortedTree(input);
			
			
			//2 - print its depth
			System.out.println("The depth of the tree is " + st.depth());
			
			//3 - print the tree nodes in ascending order
			st.traverse();
			
			System.out.println();
			st.tless(5);
			//st.level();
			
			
		}
}


