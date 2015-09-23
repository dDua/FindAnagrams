package ai2.problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

/*
 * Encapsulates the strings being looked up from anagrams or getting inserted into the dictionary
 */
public final class AnagramString {

	/*
	 * Holds the actual string text being added to dictionary or being looked for anagrams
	 */
	private String anagramString;
	
	/*
	 * Defines the retrieval type algorithm 
	 */
	private RetrievalType retrivalType;

	public static Pattern lowercaseLetters = Pattern.compile("[a-z]+");
	public static Pattern whitespace = Pattern.compile("\\s+");

	public AnagramString(String str, RetrievalType type) {
		this.anagramString = str;
		this.retrivalType = type;
	}

	/*
	 * Utility function for sorting characters of a string and converting it
	 * back to a string
	 */
	public String sortCharacters() {
		char[] letters = this.anagramString.toCharArray();
		Arrays.sort(letters);
		return String.valueOf(letters);
	}

	/*
	 * Utility function for computing the hash of a string. The hash is computed
	 * as product of prime number representation of each character. The prime
	 * can be found programmatically but this function will be called multiple
	 * times so better to use a HashMap and pre-assign the primes to individual
	 * characters
	 */
	public int getHash() {
		HashMap<Character, Integer> characterPrimeMap = new HashMap<Character, Integer>() {
			{
				put('a', 2);
				put('b', 3);
				put('c', 5);
				put('d', 7);
				put('e', 11);
				put('f', 13);
				put('g', 17);
				put('h', 19);
				put('i', 23);
				put('j', 29);
				put('k', 31);
				put('l', 37);
				put('m', 41);
				put('n', 43);
				put('o', 47);
				put('p', 53);
				put('q', 59);
				put('r', 61);
				put('s', 67);
				put('t', 71);
				put('u', 73);
				put('v', 79);
				put('w', 83);
				put('x', 89);
				put('y', 97);
				put('z', 101);
			}
		};

		// Anagrams are case-insensitive so converting to lower case
		int hash = 1;
		for (char ch : this.anagramString.toLowerCase().toCharArray()) {
			if (lowercaseLetters.matcher(String.valueOf(ch)).matches())
				if (!whitespace.matcher(String.valueOf(ch)).matches())
					hash *= characterPrimeMap.get(Character.valueOf(ch));
				else
					System.out.println(String.format("Unidentified character %s is ignored.", ch));
		}
		return hash;
	}

	/*
	 * overrides the equal method of HashMap for AnagramString type key objects
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		// Check if the object is of correct type
		if (!(obj instanceof AnagramString)) {
			return false;
		}

		// Check if the retrieval mechanism of the object is compatible with
		// object being checked against in the HashMap
		if (((AnagramString) obj).retrivalType != this.retrivalType) {
			System.out.println(String.format("Incompatible retrieval types. Expected: %s Found: %s", this.retrivalType,
					((AnagramString) obj).retrivalType));
		} else {
			switch (this.retrivalType) {
			case HASHBASED:
				return (((AnagramString) obj).getHash() == this.getHash());
			case SORTBASED:
				return (((AnagramString) obj).sortCharacters().equals(this.sortCharacters()));
			}
		}

		return false;
	}

	/*
	 * Returns custom hash values in the hashCode method of HashMap for AnagramString type key objects
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.getHash();
	}
}
