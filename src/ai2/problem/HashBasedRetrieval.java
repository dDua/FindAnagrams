package ai2.problem;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/*
 * Implements Retrieval interface and overrides the getAnagrams method 
 */
public class HashBasedRetrieval implements Retrieval<AnagramString> {

	/*
	 * Populate words dictionary with key computed from the hash function 
	 * @param dictionaryFilePath is the file type object referring to path of dictionary file
	 * @throws IOException when the file is not found when buildDictionary is called so that
	 * Main function can handle it and exit gracefully 
	 */
	public HashBasedRetrieval(File dictionaryFilePath) throws IOException{
		wordsDictionary.buildDictionary(dictionaryFilePath, RetrievalType.HASHBASED);
	}
	
	/*
	 * Overriding anagram class object to get anagrams for AnagramString type word for HashBased 
	 * retrieval type
	 * @see ai2.problem.Anagram#getAnagrams(java.lang.Object)
	 */
	@Override
	public HashSet<String> getAnagrams(AnagramString word) {	
		return wordsDictionary.get(word);
	}
}
