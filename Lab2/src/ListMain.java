
public class ListMain {

	public static void main(String[] args) {
		DynamicList myList = new DynamicList();
		DynamicList myOthrList = new DynamicList();
		DynamicList myEmpList = new DynamicList();
		
		//Inserts to create myList
		myList.insertFirst(7);
		myList.insertFirst(2);
		myList.insertFirst(1);
		
		//Inserts to create myOthrList
		myOthrList.insertFirst(4);
		myOthrList.insertFirst(5);
		
		//Display all Lists created and available
		System.out.print("List: ");
		myList.print();
		System.out.print("List: ");
		myOthrList.print();
		System.out.print("Empty list: ");
		myEmpList.print();
		
		//Append method call to join myList and myOthrList
		System.out.println("  Append...");
		System.out.print("List: ");
		myList.appendList(myOthrList);
		myList.print();
		
		//Reverse Method call to reverse myList
		System.out.println(" ");
		System.out.print("List: ");
		myList.print();
		System.out.println("  Reverse list...");
		myList.reverse();
		System.out.print("List: ");
		myList.print();
		
		//Reverse method call to reverse a list that is empty. 
		//This is to test that the method can handle empty Linked lists.
		System.out.println(" ");
		System.out.println("Reverse Empty list...");
		myEmpList.reverse();
		System.out.print("List: ");
		myEmpList.print();
		
		//Delete method call to delete the node at the center of the list.
		System.out.print("List: ");
		myList.print();
		System.out.println("  Delete mid...");
		myList.deleteMid();
		System.out.print("List: ");
		myList.print();
		
		//Delete mid method call again but with an even list.
		//This is to test that the delete mid method can handle even lists.
		System.out.println(" ");
		System.out.print("List: ");
		myList.print();
		System.out.println("  Delete mid...");
		myList.deleteMid();
		System.out.print("List: ");
		myList.print();
		
		//Delete mid method call again but with an empty list.
		//This is to test that the delete mid method can handle empty lists.
		System.out.println(" ");
		System.out.println("Delete mid in Empty List...");
		myEmpList.deleteMid();
		System.out.print("List: ");
		myEmpList.print();
	}
}
