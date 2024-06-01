package myset;

/**
 * This program demonstrates the hash set class.
 */
public class HashSetDemo {
	public static void main(String[] args) {
		//MyHashSetChaining<String> names = new MyHashSetChaining(13);
		MyHashSetLinearProbing names = new MyHashSetLinearProbing(13);

		names.add("Harry");    // 8
		names.add("Sue");      // 5
		names.add("Nina");     // 9
		names.add("Susannah"); // 4
		names.add("Larry");    // 12
		names.add("Eve");      // 11
		names.add("Sarah");    // 8 -> 9 -> 10
		names.add("Adam");	   // 0
		names.add("Tony");     // 10 -> 11 -> 12 -> 0 -> 1
		names.add("Katherine");// 9 -> 10 -> 11 -> 12 -> 0 -> 1 -> 2
		names.add("Juliet");   // 1 -> 2 -> 3
		names.add("Romeo");    // 8 -> 9 -> 10 -> 11 -> 12 -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
		names.writeOut();
		System.out.println();

		System.out.println("Size: " + names.size());
		System.out.println("Contains?? " + names.contains("Romeo"));
		names.clear();
		System.out.println("Size: " + names.size());

		// names.remove("Romeo");
		// System.out.println(names.contains("Romeo"));
		// System.out.println(names.contains("George"));
		// names.remove("George");
		// System.out.println(names.size());
		// System.out.println();
		// names.writeOut();
		// System.out.println();

	}
}
