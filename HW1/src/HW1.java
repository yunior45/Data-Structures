import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;
import com.sun.tools.javac.Main;

/*
 * standard DynamicNode Class
 */

class DynamicNode{
	public Object info;
	public DynamicNode next;

	public DynamicNode(Object x, DynamicNode n){
		info=x;
		next=n;
	}

	public Object getInfo(){
		return info;
	}

	public DynamicNode getNext(){
		return next;
	}

	public void setInfo(Object x){
		info=x;
	}

	public void setNext(DynamicNode n){
		next=n;
	}
	
	public String toString(){
		return info.toString();
	}
}

/*
 * DynamicQueue class with added methods to facilitate
 * main method operations.
 */

class DynamicQueue{
	private DynamicNode front, rear;
	
	 public DynamicQueue(){
			front=rear=null;
	 }
	 
	 public boolean empty(){
			return (front==null);
	 }
	 
	 public int queueSize(){
		 
		 /*
		  *	queueSize method determines the size
		  *	of the queue and if empty returns 0, otherwise
		  *	uses a counter and iterates through the queue
		  *	until null and returns the amount. 
		  */
		 
		 int counter = 0;
		 DynamicNode p = front;
		 if(front == null) {
			 return 0;
		 }
		 for(int z = 0; p != null;z++) {
			 counter++;
			 p = p.next;
		 }
		 return counter;
	 }
	 
	 public boolean find(Object x){
		 
		 /*
		  *	Find method traverses the queue to find
		  *	the element given. If the queue is empty
		  *	or not found it returns false, if found in
		  *	the queue it returns true. 
		  */
		 
		 if (front == null) {
			 return false;
		 }
		 DynamicNode p = front;
		 while(p != null) {
			 if(p.getInfo().equals(x)){
				 return true;
			 }
			 p = p.getNext();
		 }
		 return false;
	 }
	 
	 public void insert(Object x){
		 DynamicNode p=new DynamicNode(x,null);
		
		 if(empty()) {
			 front=rear=p;
		 }
		 
		 else {
			 rear.setNext(p); 
			 rear=p;
		 }
	 }
	 
	 public Object remove(){
		 if(empty()){
			 System.out.println("Queue underflow");
			 System.exit(1);
		 }
		 
		 DynamicNode p=front;
		 Object temp=p.getInfo();
		 front=p.getNext();
		 
		 if(front==null) {
			 rear=null;
		 }
		 return temp;
	 }
	 
	 public void print() {
		 if(front == null) {
			 System.out.print("Empty");
		 }
		 DynamicNode p = front;
		 while(p!=null) {
			 System.out.print(p.getInfo() + ((p.getNext()!=null)?"->":""));
				p = p.getNext();
			}
			System.out.println("");
	 }
	 
	 public DynamicNode insertRear(Object x){
		 
		 /*
		  * This method inserts the element in the rear of
		  * the queue. If the queue is empty it prints a
		  * line stating the queue is empty. If not empty
		  *	the queue is traversed and if reached the end and
		  *	the element is there, it returns a statement stating
		  *	that the element is in the rear. If the element is
		  *	found while traversing the queue, it is determined
		  *	if it was found at the beginning or throughout the
		  *	queue, it is then handled through the proper if statement
		  *	then queue is returned.
		  */
		 
		 if(front == null) {
			 System.out.println("Empty");
		 }
		 
		 DynamicNode z = front;
		 DynamicNode p = front;
		 DynamicNode temp = front.next;
		 DynamicNode q = front.next.next;
		 
		 while (p != null) {
			 if (p.next == null) {
				 System.out.print(x + " is already in rear. ");
			 }
			 else if(p.getInfo().equals(x)) {
				 	if (front.info == x) {					 
				 		rear.setNext(p);
				 		p.setNext(null);
				 		front = temp;
				 	}
				 	else if (p.info == x) {
				 		rear.next.setNext(p);
				 		p.setNext(null);
				 		z.next = q;			 		
				 	}
				 	System.out.print("Moving " + x + " to rear. ");
			 }			 
			 p = p.next;			
		 }
		 return p;
	 }
}

public class HW1{

	public static void main(String[] args) throws Exception {
		
		/*
		 *	File given is handled with a bufferedReader. 
		 */
		
		String path = System.getProperty("user.dir");
		File file = new File("data.txt");		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		char key = 0;
		int index = 0;
		String indexQueue = "[0-9]";
		String queueElements = "[a-z]";
		
		int N = 4;
		int QUEUESIZE = 4;
		
		DynamicQueue[] queues = new DynamicQueue[N];
		
		for (int i = 0; i < N; i++) {
			queues[i] = new DynamicQueue();
		}
		
		/*
		 * StringTokenizer handles the splitting of the data
		 * to their perspective variables.
		 */
		
		for (int i = 0; (st = br.readLine()) != null; i++) {
			
			StringTokenizer token_key = new StringTokenizer(st);						
			while(token_key.hasMoreElements()) {
				String getToken_key = token_key.nextToken();
				if(getToken_key.matches(queueElements)) {
                    key = getToken_key.charAt(0);
                }
			}
			
			StringTokenizer token_index  = new StringTokenizer(st, " ");				
			while(token_index.hasMoreElements()) {	
				String getToken_index = token_index.nextToken();
                if(getToken_index.matches(indexQueue)) {
                    index = Integer.valueOf(getToken_index);
                }
                System.out.print("Read key " + key + " for queue " + index + ". ");
			}
			
			/*
			 * keys and indexes in data are handled determining how the queue size
			 * is at the moment of handle. If the element does not find the element
			 * within the data, It is handled though the first if statement. If not
			 * found, it continues to the next if statement where is utilizes the
			 * insertRear method.
			 */
            	
            if(!queues[index].find(key)) {

                if(queues[index].queueSize() != QUEUESIZE) {
                	System.out.print("Inserting " + key + " in rear. ");
                	System.out.print("Q" + index + ": ");
                    queues[index].insert(key);
                    queues[index].print();
                }

                else if(queues[index].queueSize() == QUEUESIZE){
                	System.out.print("Q is full, removing front. ");
                    System.out.print("Inserting " + key + " in rear. ");
                    System.out.print("Q" + index + ": ");
                    queues[index].remove();
                    queues[index].insert(key);
                    queues[index].print();
                }
            }

            else if(queues[index].find(key)) {
                queues[index].insertRear(key);
                System.out.print("Q" + index + ": ");
                queues[index].print();
            }			
		}
		
		/*
		 * Final queues are printed.
		 */
		
		System.out.println(" ");
        System.out.print("..Final Queues..\n");
        
        for(int i = 0; i < N; i++) {
            System.out.print("Q" + i + ": ");
            queues[i].print();			
        }
	}
}
