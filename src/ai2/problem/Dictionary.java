package ai2.problem;

import java.io.*;
import java.util.*;

/*
 * Implements a Singleton design pattern to ensure that all AnagramString objects lookup the same dictionary
 */
public class Dictionary {

	/*
	 * Holds the Dictionary class object
	 */
	private static Dictionary instance = null;
	
	/*
	 * Actual map which holds the words and their Anagrams
	 */
	private static HashMap<AnagramString, HashSet<String>> map = new HashMap<AnagramString, HashSet<String>>();

	private Dictionary() {
	}

	/*
	 * Return Singleton Dictionary class instance.
	 */
	public static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}
		return instance;
	}

	/*
	 * Populate the dictionary instance with words from the given file based on retrieval type
	 * @param dictionaryPath is the File type object of path of dictionary file
	 * @param type is an enum defined with two value - SORTBASED, HASHBASED
	 * retrieval type SORTBASED : word "tabs" is sorted to "abst" and used as key to Dictionary 
	 * retrieval type HASHBASED : words "tabs" is converted to a hashCode using product of primes as hash-function
	 * @throws IOException so that the Main class can deal with it and exit gracefully when the dictionary file
	 * is not found
	 */
	public void buildDictionary(File dictionaryPath, RetrievalType type) throws IOException {

		try {
			BufferedReader dictionaryReader = new BufferedReader(new FileReader(dictionaryPath));
			String word = "";
			while ((word = dictionaryReader.readLine()) != null) {
				AnagramString key = new AnagramString(word, type);
				
				// get reference to the HashSet value for key of type AnagramString
				HashSet<String> value = map.get(key);
				
				// if the key is not already present in the HashMap, null is returned
				if(value == null){
					value = new HashSet<String>();
					map.put(key, value);
				}
				
				// add word to the corresponding HashSet value of key
				// HashSet instance "value" is a reference to the value slot of HashMap so don't need to 
				// perform a separate put operation in HashMap after updating the value
				value.add(word); 
			}
			dictionaryReader.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/*
	 * Return list of anagrams for a word from dictionary
	 * @param str is of type AnagramString
	 * @returns HashSet of anagrams if found in the dictionary for word, otherwise returns null
	 */
	public HashSet<String> get(AnagramString str) {
		if (map.containsKey(str))
			return map.get(str);
		else
			return null;
	}
}
