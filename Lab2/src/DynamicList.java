
public class DynamicList {
	private DynamicNode list;
	
	public DynamicList() {
		list = null;
	}
	
	public boolean isEmpty() {
		return list == null;
	}
	
	public void insertFirst(Object x){
		DynamicNode q = new DynamicNode(x,null);
		
		if(!isEmpty()) {
			q.setNext(list);
		}
		list = q;
	}
	
	public DynamicNode getList() {
		return list;
	}
	
	public void print() {
		if (list == null) {
			System.out.println(" ");
		}
		DynamicNode p = list;
		while(p!=null) {
			System.out.print(p.getInfo() + ((p.getNext()!=null)?" ":""));
			p = p.getNext();
		}
		System.out.println();
	}
	
	public boolean appendList(DynamicList othrList) {
		DynamicNode q = othrList.getList();	// List that the method was called with.
		DynamicNode p = this.getList();	// Primary list 
		
		/*
		 * *If either list is empty, method returns false.
		 * if true, node last finds last node and sets the next node
		 * starting at the head of node q.
		 */
		
		if(othrList.isEmpty() || this.isEmpty()) {
			return false;
		}
		else {			
			DynamicNode last = p.getNext().getNext();
			last.setNext(q);			
		}
		return true;
	}
	
	public void reverse() {
		DynamicNode p = this.getList(); // List to be traversed
		DynamicNode q = null;			// Dummy node
		DynamicNode temp = null;		// dummy node

		/*
		 * If the list is empty, an error message is printed.
		 * If not, the list is traversed using a while statement
		 * and the pointer is moved to the last node. When printed,
		 * The nodes are then printed in reverse order. 
		 */
		
		if(p == null) {
			System.out.println("Error: List is empty.");
		}
		else {
			 while(p != null) {
				 temp = p.getNext();
				 p.next = q;
				 q = p;
				 p = temp;
			 }
			 list = q;			 
		}
	}
	
	public Object deleteMid() {
		DynamicNode p = this.getList(); // List used to call method.
		
		/* 
		 * If the list is empty, an error message is printed.
		 * if the list is even, an error message is printed.
		 * If criteria is met, the list is traversed to the middle,
		 * and assigned to node q. object temp is assigned with
		 * the node to be deleted. node p is set with the next node
		 * of q and returned temp.
		 */
		
		if(p == null) {
			System.out.println("Error: The List is empty.");
			return p;
		}
		else if( p.getNext().getNext().getNext().getNext() == null) {
			System.out.println("Error: The list is even, no middle node to remove.");
			return p;
		}
			DynamicNode q = p.getNext().getNext().getNext();
			Object temp = p.getNext().getNext();
			p.getNext().setNext(q);
			return temp;
	}
}
























