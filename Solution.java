package SolutionsHackerRank;

/**
 * Solution for ColoringTree problem from 101 Hack February on www.hackerrank.com
 * Adrian Spanu
 * 2014-04-01
 */

import java.awt.*;
import java.util.*;

public class Solution {

    private static String[] map;
    private static int pathLength;
    private static int currPathLength;
    private static String[] shortestPath;
    private static final char TREE = 'X';
    private static final char HERMIONE = 'M';
    private static final char EXIT = '*';
    private static final char EMPTY = '.';
    private static final char PATH = '+';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numTests = scanner.nextInt();

        for (int i = 0; i < numTests; i++) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            Point start = new Point(); //Where Hermione starts
            Point end = new Point(); //The exit
            map = new String[rows];
            shortestPath = new String[rows];
            pathLength = rows * columns; //Worst case scenario - all paths will be better than this
            currPathLength = 0; //Initialize
            scanner.nextLine(); //Get rid of the end of the last int
            for (int j = 0; j < rows; j++) {
                map[j] = scanner.nextLine();
                if (map[j].contains(Character.toString(HERMIONE))) {
                    start.x = map[j].indexOf(Character.toString(HERMIONE));
                    start.y = j;
                } //if
            } //for rows

            //Populate the real path
            findPath(start);

            //Now go through the real path and find the number of locations which needed multiple checks
            int numWands = pathWands(shortestPath);

            int numWandsIn = scanner.nextInt();
            if (numWandsIn == numWands)
                System.out.println("Impressed");
            else
                System.out.println("Oops!");

        } //for numTests

    } //main

    private static String pathToString(String[] path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            stringBuilder.append(path[i]);
            stringBuilder.append("\n");
        } //for
        return stringBuilder.toString();
    }

    private static int pathWands(String[] shortestPath) {
        //Go through the path given and check to see how many path locations have at least 1 '.' next to them
        int numWands = 0;
        for (int i = 0; i < shortestPath.length; i++) {
            if (shortestPath[i].contains(Character.toString(PATH))) {
                for (int j = 0; j < shortestPath[i].length(); j++) {
                    if (shortestPath[i].charAt(j) == PATH && hasEmptyAround(new Point(j, i),shortestPath)) {
                        numWands++;
                    } //if
                } //for
            } //if
        } //for
        return numWands;
    } //pathWands

    private static boolean hasEmptyAround(Point pt, String[] path) {
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;

        if (ptInsideMap(new Point(pt.x, pt.y-1),path) && isEmpty(new Point(pt.x, pt.y - 1), path))
            north = true;
        if (ptInsideMap(new Point(pt.x+1, pt.y),path) && isEmpty(new Point(pt.x + 1, pt.y), path))
            east = true;
        if (ptInsideMap(new Point(pt.x, pt.y+1),path) && isEmpty(new Point(pt.x, pt.y + 1), path))
            south = true;
        if (ptInsideMap(new Point(pt.x-1, pt.y),path) && isEmpty(new Point(pt.x-1, pt.y), path))
            west = true;

        if (north || east || south || west)
            return true;
        return false;
    } //hasEmptyAround


    public static boolean ptInsideMap(Point pt, String[] map) {
        if (pt.y < 0 || pt.y >= map.length)
            return false;
        if (pt.x < 0 || pt.x >= map[0].length())
            return false;
        return true;
    } //ptInsideMap

    public static boolean findPath(Point here) {
        if (!ptInsideMap(here, map))
            return false;
        if (map[here.y].charAt(here.x) == EXIT) {
            //Reached the goal, if this is the shortest path, copy it over:
            if (currPathLength < pathLength) {
                //Copy over the current map!
                for (int i = 0; i < map.length; i++) {
                    shortestPath[i] = new String(map[i]);
                } //for
            } //if
            return true;
        } //if
        if (!isEmpty(here,map))
            return false;

        //Mark this on the path
        StringBuilder thisLine = new StringBuilder(map[here.y]);
        thisLine.setCharAt(here.x, PATH);
        map[here.y] = thisLine.toString();
        currPathLength++;

        //Add each direction recursively
        findPath(new Point(here.x + 1, here.y));
        findPath(new Point(here.x - 1, here.y));
        findPath(new Point(here.x, here.y + 1));
        findPath(new Point(here.x, here.y - 1));

        thisLine.setCharAt(here.x,EMPTY);
        map[here.y] = thisLine.toString();
        currPathLength--;

        return false;
    } //findPath

    private static boolean isEmpty(Point pt, String[] path) {

        if (path[pt.y].charAt(pt.x) == TREE)
            return false;
        if (path[pt.y].charAt(pt.x) == PATH)
            return false;
        if (path[pt.y].charAt(pt.x) == EXIT)
            return false;
        return true;
    }

} //Solution