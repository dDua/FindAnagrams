package ai2.problem;

import java.io.File;
import java.util.HashSet;

/*
 * Implements Retrieval interface and overrides the getAnagrams method 
 */
public class SortBasedRetrieval implements Retrieval<AnagramString> {

	/*
	 * Populate words dictionary with key as sorted character sequence as string 
	 * @param dictionaryFilePath is the file type object referring to path of dictionary file
	 * @throws IOException when the file is not found when buildDictionary is called so that
	 * Main function can handle it and exit gracefully 
	 */
	public SortBasedRetrieval(File dictionaryFilePath) throws Exception{
		wordsDictionary.buildDictionary(dictionaryFilePath, RetrievalType.SORTBASED);
	}
	
	/*
	 * Overriding anagram class object to get anagrams for AnagramString type word for SortBased 
	 * retrieval type
	 * @see ai2.problem.Anagram#getAnagrams(java.lang.Object)
	 */
	@Override
	public HashSet<String> getAnagrams(AnagramString word) {
		return wordsDictionary.get(word);
	}
	
}
