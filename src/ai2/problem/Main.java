package ai2.problem;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Default dictionary path. Can also be provided in command-line arguments
		File dictionaryFile = new File("./dataset/dictionary.txt");

		if (args.length <= 1) {
			System.out.println(getUsageString());
			System.exit(0);
		}

		try {
			int i = 0;
			List<String> inputWords = new ArrayList<String>();
			RetrievalType type = RetrievalType.SORTBASED;
			Retrieval<AnagramString> anagram;

			// Parse command-line arguments
			while (i < args.length) {
				if (args[i].toLowerCase().equals("-dictfile")) {
					dictionaryFile = new File(args[++i]);
				} else if (args[i].toLowerCase().equals("-words")) {
					// get the argument after the keyword -words
					inputWords.addAll(Arrays.asList(args).subList(++i, args.length));
					i += inputWords.size();
				} else if (args[i].toLowerCase().equals("-retrievaltype")) {
					// get the argument after the keyword -retrievaltype
					type = args[++i].toLowerCase().equals("hashbased") ? RetrievalType.HASHBASED
							: RetrievalType.SORTBASED;
				} else {
					System.out.println(getUsageString());
					System.exit(0);
				}
				i++;
			}

			// Instantiate anagram object based on the retrieval type which further builds the dictionary in the constructor
			anagram = type.equals(RetrievalType.SORTBASED) ? new SortBasedRetrieval(dictionaryFile)
					: new HashBasedRetrieval(dictionaryFile);

			// Generate list of anagrams
			for (String input : inputWords) {
				if (input != null && !input.isEmpty()) {
					AnagramString key = new AnagramString(input, type);
					// returns list of anagrams if found else returns null
					HashSet<String> anagrams = anagram.getAnagrams(key);
					if (anagrams == null) {
						System.out.println(String.format("\nNo anagram found for the word : %s ",input));
					} else {
						System.out.println(String.format("\nAnagrams for the word \"%s\" are:", input));
						for (String a : anagrams) {
							System.out.println(String.format("%s ", a));
						}
					}
				} else {
					System.out.println("words not specified in the arguments");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(String.format("Cannot read dictionary file at %s", dictionaryFile.getAbsolutePath()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static String getUsageString(){
		String message = "Correct usage: java -cp . ai2.problem.Main [-options] -words [<list of words>] \n"
				+ "where -options include : \n"
				+ "\t -retrievaltype [HASHBASED | SORTBASED] \n"
				+ "\t -dictFile <dictionary_path> \n"
				+ "\nExample Usage 1 : java -cp . ai2.problem.Main -words tabs stab"
				+ "\nExample Usage 2 : java -cp . ai2.problem.Main "
				+ "-dictfile  ./dataset/dictionary.txt -retrievaltype HASHBASED -words tabs stab";
		return message;
	}
}
