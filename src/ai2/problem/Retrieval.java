package ai2.problem;

import java.util.HashSet;

/*
 * This interface defines the type of anagram retrieval algorithms that can be performed
 */
public interface Retrieval<T> {

	// The Singleton Dictionary instance which contains all the words in input dictionary file and their anagrams
	public static Dictionary wordsDictionary = Dictionary.getInstance();
	
	/*
	 *  Retrieves anagrams for a given word 
	 *  @param word of type AnagramString class
	 *  @returns HashSet containing strings from dictionary that are anagrams of word
	 */
	public HashSet<String> getAnagrams(T word);
}
