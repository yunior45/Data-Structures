
public class DynamicNode {
	private Object info;
	public DynamicNode next;
	
	public DynamicNode(Object x, DynamicNode n) {
		info = x;
		next = n;	
	}
	
	public Object getInfo() {
		return info;
	}
	
	public DynamicNode getNext() {
		return next;
	}
	
	public void setInfo(Object x) {
		info = x;
	}
	
	public void setNext(DynamicNode n) {
		next = n;
	}
	
	public String toString() {
		return info.toString();
	}
}
