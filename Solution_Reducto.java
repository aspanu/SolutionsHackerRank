package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution_Reducto {

    private static int minWordLength;

    public static void main(String args[] ) throws Exception {


        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int numWords = scanner.nextInt();
            String originalWord = scanner.next();
            List<String> wordsToRemove = new ArrayList<String>(numWords);
            int minWordToRemoveLength = originalWord.length();
            for (int j = 0; j < numWords; j++) {
                wordsToRemove.add(scanner.next());
                if (wordsToRemove.get(j).length() < minWordToRemoveLength) {
                    minWordToRemoveLength = wordsToRemove.get(j).length();
                } //if
            } //for
            minWordLength = originalWord.length(); //Maximum value
            removeWords(originalWord, minWordToRemoveLength, wordsToRemove);
            System.out.println(minWordLength);
        } //for
    } //knightFinder.lineFinder.longestBalancedSubstring.main

    private static void removeWords(String word, int minWordToRemoveLength, List<String> wordsToRemove) {
        if (word.length() < minWordToRemoveLength) {
            minWordLength = word.length();
            return;
        } //if

        //Go through each word to remove in the list and try to remove it from the word remaining. If possible, then
        // recursively call removeWords
        for (String toRemove : wordsToRemove) {
            if (word.contains(toRemove)) {
                //Then we need to remove that from the word!
                String tempWord = (word.substring(0,word.indexOf(toRemove)) +
                        word.substring(word.indexOf(toRemove) + toRemove.length(), word.length()));
                removeWords(tempWord, minWordToRemoveLength, wordsToRemove);
                if (tempWord.length() < minWordLength) {
                    minWordLength = tempWord.length();
                }
            } //if
        } //for
    } //removeWords
} //Solution_PuzzleFinder