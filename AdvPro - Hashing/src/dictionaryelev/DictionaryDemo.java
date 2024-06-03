package dictionaryelev;

public class DictionaryDemo {

	public static void main(String[] args) {
		Dictionary dictionary = new DictionaryHashMap<Integer,String>();

		System.out.println("Is dictionary empty? " + dictionary.isEmpty());
		System.out.println("Dictionary size: " + dictionary.size());

		dictionary.put(8, "hans");
		dictionary.put(8, "lars");
		dictionary.put(3, "viggo");
		System.out.println("Is dictionary empty? " + dictionary.isEmpty());
		System.out.println("Dictionary size: " + dictionary.size());

		System.out.println("Getting spot 8: " + dictionary.get(8));

		dictionary.put(7, "bent");
		dictionary.put(2, "lene");
		System.out.println("Is dictionary empty? " + dictionary.isEmpty());
		System.out.println("Dictionary size: " + dictionary.size());
		System.out.println("Removing 3: " + dictionary.remove(3));

		System.out.println("Dictionary size: " + dictionary.size());

		System.out.println("Putting Ida to spot 8: " + dictionary.put(8, "Ida"));
		System.out.println("Getting spot 8: " + dictionary.get(8));

	}
}
