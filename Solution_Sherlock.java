package SolutionsHackerRank;

/**
 * Solution for Sherlock problem from 101 Hack February on www.hackerrank.com
 * Adrian Spanu
 * 2014-04-01
 */


import java.util.*;

public class Solution_Sherlock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numTests = scanner.nextInt();

        for (int i = 0; i < numTests; i++) {
            int numVals = scanner.nextInt();
            HashMap<Integer, Long> list = new HashMap<Integer, Long>(numVals);
            for (int j = 0; j < numVals; j++) {
                int nextIn = scanner.nextInt();
                if (!list.containsKey(nextIn))
                    list.put(nextIn,0L);
                list.put(nextIn, list.get(nextIn)+1);
            } //for

            long pairs = 0;
            for (Long val : list.values())
                pairs = pairs + ( val * (val - 1));
            //for
            System.out.println(pairs);
        } //for

    } //main
} //Solution