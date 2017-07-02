package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution_PerfectPartner {

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        for (int i = 0; i < tests; i++) {
            int numPairs = scanner.nextInt();
            List<Integer> men = new ArrayList<Integer>(numPairs);
            List<Integer> women = new ArrayList<Integer>(numPairs);
            for (int j = 0; j < numPairs; j++) {
                men.add(scanner.nextInt());
            } //for
            for (int j = 0; j < numPairs; j++) {
                women.add(scanner.nextInt());
            } //for

            Collections.sort(men);
            Collections.sort(women);

            long differences = 0l;
            for (int j = 0; j < numPairs; j++) {
                differences = differences + Math.abs(men.get(j) - women.get(j));
            } //for
            System.out.println(differences);
        } //for
    } //knightFinder.lineFinder.longestBalancedSubstring.main

} //Solution_PuzzleFinder