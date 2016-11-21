import java.util.Scanner;

public class TreeTester {
	public static void main(String[]args) {

		Scanner kb = new Scanner(System.in);

		AVLTreeMap bst = new AVLTreeMap();

		System.out.println("Hello");

		String input = kb.next();
		
		while (!input.equals("!")) {
			if (input.equals("add")) {
				System.out.println("What to add: ");
				bst.insert(kb.nextInt(),"A");
			} else if (input.equals("remove")) {
				System.out.println("What to remove: ");
				bst.remove(kb.nextInt());
			} else if (input.equals("print")) {
				System.out.println(bst);
			} else if (input.equals("has")) {
			System.out.println("What: ");
			System.out.println(bst.has(kb.nextInt()));
			}
			input = kb.next();

		}

		/*TreapMap treap = new TreapMap();

		for (int i = 0; i < 5; i++) {
			treap.insert(i, "A");
		}

		System.out.println(treap); */


		//System.out.println(recursive(4));
	}

	/*public static String recursive(int i) {

		if (i == 0) {
			return "";
		} else {
			return i + "" + recursive(i-1);
		}

	} */
}