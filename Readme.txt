Find all the Anagrams of a given word from an available dictionary

1.) cd <FindAnagrams>
2.) mkdir bin
3.) javac -d bin/ src/ai2/problem/*.java
4.) java -cp ./bin/ ai2.problem.Main -dictFile <dictionary_path> -retrievaltype [HASHBASED | SORTBASED] -words [<list of words>]

This problem can be approached in two ways-

1.) High Accuracy
	
	The idea here is to sort the characters of the word and use that to key into the HashMap with value as set of words 
	from dictionary file that share the same key.
	
	The time complexity of this algorithm depends on the sorting technique used and in my current implementation it is O(nlogn),
	where n is the number of characters in the string, so total time complexity is O(nlogn * |dictionary size|)) and space 
	complexity is O(|dictionary size|). For each lookup during the test phase it would require O(nlogn) time as each string would
	be sorted. Java's internal sorting algorithm is MergeSort which is why this is O(nlogn). It can be improved with Counting Sort 
	to O(n) but if the list of words is very large the space complexity grows linearly with number of words in dictionary. 
	
	This approach has high accuracy as it will always return the exact list of anagrams.

1.) Low Response Time
 
	The idea here is to map every word to an integer value with a hash-function. The hash-function used is the product of
	individual characters in the word mapped to distinct prime numbers.
	This approach is very fast as as it requires O(n) time to map the word to hash and then lookup is O(1) from the dictionary.
	If we need to retrieve anagrams from the dictionary a lot or if the length of the strings is huge, say sentences, then O(n)
	is faster over O(nlogn). However, it being a hashing technique their are standard problems of collisions which are avoided 
	by using prime number but can still happen so can't be ignored. This can lead to mulytiple strings hashing to same hashkey 
	so it can be a bit less accurate. We can use different collision detection techniques to solve
	this problem which might increase the time complexity in worst case scenario.
	
	
	Note:-
	1.) Anagrams are independent of letter case and spaces in the string so that has been ignored in the code.
	2.) The dictionary object is implemented as a Singleton so that multiple anagram objects can access the same instance, however,
		if the retrieval type is being changed from SortBased to HashBased then the dictionary needs to be rebuilt in which case
		using a Factory-Pattern would be a better approach. Depending on the usage it can be designed appropriately.
