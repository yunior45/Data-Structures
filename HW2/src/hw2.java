
public class hw2 {
	
	/*  
	 * Definitions of the parameters
	 *    1) heap: the array where the heap (sweeping window) is implemented
	 *    2) newEle: the new element to insert
	 *    3) pos: where to insert the new element initially.
	 *            note it does not mean newEle is going to 
	 *            stay at pos after this function
	 *    4) increment
	 *    	a) true: insert newEle, that is all
	 *    	b) false: insert newEle, then remove the root
	 */
	
	/*
	 * 	If the increment boolean is set to true,
	 * 	The first if statement constraint is met.
	 * 	The element is added to the position node
	 * 	and then bubbled down the heap. If the
	 * 	increment is false and the new element is
	 *  of lesser value then the root element, the
	 *  second if statement is met. In the second if
	 *  statement, The new element is added in the 
	 *  position node and attempted to bubble up as far
	 *  as it can. The element at the position node is then
	 *  swapped with the element at the root node. the element
	 *  at the root node is then bubbled down as far as it
	 *  can.
	 */
	static void insertAt(int [] heap, int newEle, 
					int pos, boolean increment){
		
		int temp; 		// temporary Node
		int child; 		// Child Node
		int parent; 	// parent Node
		int lc;			// Left Child Node
		int rc; 		// Right Child Node
		int larger;		// Larger child element

		/*
		 * 	first if statement where the constraint
		 * 	is if the increment is true.
		 */
		
		if(increment == true) {
			heap[pos] = newEle;
			
			for(int i = 0; i <= pos; i++) {
				
				parent = (i-1)/2;
				lc = (2*parent)+1;
				rc = (2*parent)+2;
				larger = (heap[lc] > heap[rc])? lc: rc;
				
				while(heap[parent] < heap[larger]) {
					temp = heap[larger];
					heap[larger] = heap[parent];
					heap[parent] = temp;
					larger = parent;
					parent = (larger-1)/2;
				}	
			}	
		}
		
		/* 
		 * 	Second if statement where the constraint
		 * 	is if the increment is false and the new
		 * 	element is of lesser value then the root 
		 * 	element.
		 */
		
		else if(increment == false && heap[0] > newEle){
						
			heap[pos] = newEle;
			
			for(int i = heap.length-1; i>0 ;i--) {				
				child = i;
				parent = (i-1)/2;
				
				while(heap[child] > heap[parent]) {
					temp = heap[child];
					heap[child] = heap[parent];
					heap[parent] = temp;
					child = parent;
					parent = (child-1)/2;
				}
			}
			
			heap[0] = heap[pos];

			for(int i = 0; i < pos; i++) {				
				parent = (i-1)/2;
				lc = (2*parent)+1;
				rc = (2*parent)+2;
				larger = (heap[lc] > heap[rc])? lc: rc;
				
				while(heap[parent] < heap[larger]) {
					temp = heap[larger];
					heap[larger] = heap[parent];
					heap[parent] = temp;
					larger = parent;
					parent = (larger-1)/2;
				}				
			}
		}		
	}
	
	/*
	 * get the smallest k elements in array x in O(nlogk) time, where
	 * n is the size of array x.
	 * 
	 * It is supposed to return an array, containing the smallest k elements
	 * in the increasing order.
	 */
	static int [] getSmallestK(int x[], int k){
		
		if(k > x.length) 
			return null;
		
		int [] result = new int[k+1];
		
		// use the first k elements in x to construct an 
		// almost complete binary tree, where parent >= children
		result[0] = x[0];
		for(int i = 1; i < k; i++){
			insertAt(result, x[i], i, true);
		}
		
		System.out.print("Original heap: ");
		for(int i = 0; i < k; i++){
			System.out.print(result[i]+ " ");
		}
		System.out.println();

		// for each element in the rest of array x, 
		// insert it in the almost complete binary tree, and then
		// remove the root in the tree. 
		for(int i = k; i < x.length; i++){
			insertAt(result, x[i], k, false);
		}
		
		// now the first k elements in result are the smallest k elements in x
		System.out.print("Resulting heap: "); 
		for(int i = 0; i < k; i++){
			System.out.print(result[i]+ " ");
		}
		System.out.println();
		
		// sort the first k elements in result in O(klogk) time
		
		/*
		 * 	The following for loop is to sort the resulting
		 * 	array in ascending order. The for loop initially
		 * 	starts of the size of the result array. The value
		 * 	at result[i] is swapped with the root element and
		 * 	then bubbled down. if none of the cases are met,
		 * 	The else statement will search and assign the largest
		 * 	left or right child and then the while loop will 
		 * 	attempt to bubble down the heap in that direction
		 *  while intending to put the next largest element 
		 *  in the root position for the next for loop iteration.
		 */
		
		for(int i = result.length-2;i >= 0; i--) {	
			int larger;	// Larger left or right child
			
			int temp = result[0];
			result[0] = result[i];
			result[i] = temp;
			
			if(i-1==0) {
				break;
			}
			if(i-1==1) {
				larger = 1;
			}
			else {
				larger = (result[1]>result[2])?1:2;
			}

			int child = larger;
			int parent = (child-1)/2;
			
			while (child<=i && result[child]>result[parent]) {
				temp = result[child];
				result[child] = result[parent];
				result[parent] = temp;
				parent = child;
				child = (2*parent)+1;
			}			
		}
		return result;		
	}

	public static void main(String[] args) {
		// Test cases		
				int [] data = {31, 44, 64, 5, 61,
								43, 6, 88, 59, 90,
								39, 97, 77, 62, 99,
								34, 57, 53, 60, 29};
				
				int i, k = 5;		
				System.out.println(" k = " + k);
				
				int [] largestK = getSmallestK(data, k);
				
				System.out.print("Sorted result (smallest k elements): "); 
				for(i=0;i<k;i++){
					System.out.print(largestK[i]+ " ");
				}
				
				k = 8;		
				System.out.println("\n k = " + k);
				
				largestK = getSmallestK(data, k);
				
				System.out.print("Sorted result (smallest k elements): "); 
				for(i=0;i<k;i++){
					System.out.print(largestK[i]+ " ");
				}
	}

}
