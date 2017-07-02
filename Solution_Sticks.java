package SolutionsHackerRank;

/**
 * Solution_PuzzleFinder for Sticks problem from 101 Hack February on www.hackerrank.com
 * Adrian Spanu
 * 2014-04-01
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution_Sticks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSticks = scanner.nextInt();
        List<Integer> sticks = new LinkedList<Integer>();
        for (int i = 0; i < numSticks; i++) {
            sticks.add(scanner.nextInt()); //Get the list of sticks
        } //for
        //In this list of sticks, find the min, and then re-iterate over them to remove min

        boolean emptyList = false;
        while (!emptyList) {
            System.out.println(sticks.size()); //Show the size

            emptyList = subtractAndRemoveSticks(sticks);
        } //while

    } //knightFinder.lineFinder.longestBalancedSubstring.main

    private static boolean subtractAndRemoveSticks(List<Integer> sticks) {
        //Take the list, find the minimum, subtract the minimum from each element, remove all elements that are zero
        int minVal = Collections.min(sticks);

        for (int i = sticks.size() - 1; i >= 0; i--) { //Go backwards so we don't run into issues while deleting elements
            if (sticks.get(i) == minVal) {
                sticks.remove(i);
            } //if
            else
                sticks.set(i, sticks.get(i) - minVal);
        } //for

        if (sticks.size() == 0) {
            return true;
        }

        return false;
    } //subtractAndRemoveSticks
}