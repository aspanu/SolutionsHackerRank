package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_FizzBuzz {
    private static int bestScore;
    private static ArrayList<ArrayList<Integer>> bestCluster;

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numTo = scanner.nextInt();

        for (int i = 1; i <= numTo; i++) {
            if (i % 5 == 0  && i % 3 == 0) {
                System.out.println("FizzBuzz");
            } //if
            else if (i % 5 == 0) {
                System.out.println("Buzz");
            } //else if
            else if (i % 3 == 0) {
                System.out.println("Fizz");
            } //else if
            else
                System.out.println(i);
        } //for

    } //knightFinder.lineFinder.longestBalancedSubstring.main

} //Solution_PuzzleFinder