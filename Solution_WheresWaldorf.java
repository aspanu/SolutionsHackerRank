package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_WheresWaldorf {

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numTests= scanner.nextInt();
        for (int i = 0; i < numTests; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            scanner.nextLine();

            ArrayList<String> puzzle = new ArrayList<String>(m);
            for (int j = 0; j < m; j++) {
                puzzle.add(scanner.nextLine().toLowerCase());
            } //for

            //Check that we loaded the correct puzzle:
            printPuzzle(puzzle);

            int numWords = scanner.nextInt();

            for (int j = 0; j < numWords; j++) {
                System.out.println(findWordInPuzzle(puzzle, scanner.next().toLowerCase()));
            } //for

        } //for
    } //main

    private static String findWordInPuzzle(ArrayList<String> puzzle, String word) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < puzzle.size(); i++) {
            if (puzzle.get(i).contains(String.valueOf(word.charAt(0)))) {
                if ( lookHori(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ||
                lookVert(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ||
                lookDiag(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ||
                lookHori2(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ||
                lookVert2(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ||
                lookDiag2(puzzle, word, i, puzzle.get(i).indexOf(String.valueOf(word.charAt(0)))) ) {
                    stringBuilder.append(i + 1);
                    stringBuilder.append(" ");
                    stringBuilder.append(puzzle.get(i).indexOf(String.valueOf(word.charAt(0))) + 1);
                    break;
                } //if
            } //if
        } //for

        return stringBuilder.toString();
    } //findWordInPuzzle

    private static boolean lookVert(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if ( puzzle.size() > row + i && !(puzzle.get(row + i).charAt(col) == word.charAt(i)))
                return false;
        } //for
        return true;
    }

    private static boolean lookDiag(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if ( puzzle.size() > row + i && puzzle.get(row + i).length() > col + i && !(puzzle.get(row + i).charAt(col + i) == word.charAt(i)))
                return false;
        } //for
        return true;
    }

    private static boolean lookHori(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if (puzzle.get(row).length() > col + i && !(puzzle.get(row).charAt(col + i) == word.charAt(i)))
                return false;
        } //for
        return true;
    }

    private static boolean lookVert2(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if (row - i < 0 || !(puzzle.get(row - i).charAt(col) == word.charAt(i)))
                return false;
        } //for
        return true;
    }

    private static boolean lookDiag2(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if ( row - i < 0 || col - i < 0 || !(puzzle.get(row - i).charAt(col - i) == word.charAt(i)))
                return false;
        } //for
        return true;
    }

    private static boolean lookHori2(ArrayList<String> puzzle, String word, int row, int col) {
        for (int i = 1; i < word.length(); i++) {
            if (col - i < 0 || !(puzzle.get(row).charAt(col - i) == word.charAt(i)))
                return false;
        } //for
        return true;
    }


    private static void printPuzzle(ArrayList<String> puzzle) {
        for (String s : puzzle) {
            System.out.println(s);
        } //for
    } //printPuzzle

} //Solution