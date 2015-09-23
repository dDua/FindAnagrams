Find all the Anagrams of a given word from an available dictionary

Prereqisites - Java 7 or higher

1.) cd <FindAnagrams>
2.) mkdir bin
3.) javac -d bin/ src/ai2/problem/*.java
4.) java -cp ./bin/ ai2.problem.Main -dictFile <dictionary_path> -retrievaltype [HASHBASED | SORTBASED] -words [<list of words>]

This problem can be approached in two ways-

1.) High Accuracy
	
The idea here is to sort the individual characters of the word and use that to key into the HashMap with value as set of words from dictionary file that are anagrams to th ekey.
For instance, "tabs" is converted to "abst" and used as key to the hashmap. 
	
The time complexity of this algorithm depends on the sorting technique used and in the current implementation it is O(nlogn), where n is the number of characters in the string, so total time complexity is O(nlogn * |dictionary size|)) and space complexity is O(|dictionary size|). 
For each lookup during the test phase it would require O(nlogn) time as each string would be sorted. Java's internal sorting algorithm is MergeSort which is why this is O(nlogn). 
It can be improved with Counting Sort to O(n) but if the words are very long, say sentences, the space complexity grows linearly with number of characters in the word. 
	
This approach has high accuracy as it will always return the exact list of anagrams.

1.) Low Response Time
 
The idea here is to map every word to an integer value with a hash-function. The hash-function used is the product of individual characters in the word mapped to distinct prime numbers.
For instance, "tabs" is 71*2*3*67 = 28542
This approach is very fast as as it requires O(n) time to map the word to hash so the total complexity to building dictionary is O(n*|dictionary size|) and space complexity is O(|dictionary size|).
For each lookup during the test phase it would need O(n) time to map teh string to hashcode and then looking up in dictionary is O(1).
If we need to retrieve anagrams from the dictionary frequently or the strings indexed in dictionary are significantly longer then O(n) will perform better over O(nlogn). 
However, it being a hashing technique their are standard problems of collisions which are reduced by using prime number but can still happen especially when the product of primes overflows int range and rotates back to negative range, so can't be ignored. 
This can lead to multiple strings hashing to same hashkey so it can be a bit less accurate. We can use different collision detection techniques to solve this problem which might increase the time complexity in worst case scenario depending on collsion resolution technique.
Currently, it handles only alphabets if there are special charcters like "tabs!" the ! is ignored and anagrams for "tabs" are returned. All the special symbols can be either pre-assigned prime numbers by using a prime number generator in the intializer once or can be added to the map as and when they are seen.
As of now a static map has been kept for all alphabets as prime-number generator are computationally intensive. 

	
Note:-
1.) Anagrams are independent of letter case and spaces in the string so that has been ignored in the code.
2.) The dictionary object is implemented as a Singleton so that multiple anagram objects can access the same instance, however, if the retrieval type is being changed from SortBased to HashBased then the dictionary needs to be rebuilt in which case using a Factory-Pattern would be a better approach. Depending on the usage it can be designed appropriately.
 
The code is organized as below-
Retrieval.java - interface the can be overidden by different anagram retrieval algorithms
SortBasedRetrieval.java and HashBaseRetrieval.java - two different retrieval mechanisms based on two schemes described above. implemented by overriding teh above interface.
Dictionary.java - singleton implementation of the dictionary object, so that all AnagramString objects lookup the same dictionary.
AnagramString.java - encapsulated the strings given in dictionary and override the equals() and hashCode() method for accumulating anagrams in the dictionary object.
Main.java - driver programs that parses the command-line and looks up for anagrams of the string.