package tp2e;

public class Main {

	public static void main(String[] args) {
		
		Tree pine = new Tree(8);
		
		pine.add(5);
		pine.add(6);
		pine.add(9);
		pine.add(2);
		pine.add(4);
		pine.add(1);
		pine.add(20);
		pine.add(15);
		pine.add(25);
		
		
		
		pine.printInOrder();
		System.out.println("");
		
		System.out.println(pine.delete(0));
		
		
		pine.printInOrder();
		System.out.println("");
		
		
	
		
		
//		ArrayList<Integer> leaves = pine.getFrontera(); 
//		
//		for(int leaf : leaves)
//			System.out.print(leaf + " ");
//		
//		System.out.println(" ");
//		
//		ArrayList<Integer> level2 = pine.getElemAtLevel(2);
//		
//		for(int elem : level2)
//			System.out.print(elem + " ");
	
		
	}

}
