package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution_Singularity {


    public static final String QUERY_ADD = "add";
    public static final String QUERY_CALC = "calculate";
    public static final int QUERY_ADD_NUM = 4;
    public static final int QUERY_CALC_NUM = 6;

    static class PointMass {
        public int x;
        public int y;
        public int z;
        public long  mass;

        PointMass(int x, int y, int z, long mass) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.mass = mass;
        }

        public boolean isWithin(PointMass a, PointMass b) {
            if (x >= a.x && y >= a.y && z >= a.z && x <= b.x && y <= b.y && z <= b.z)
                return  true;

            return false;
        } //isWithin

    } //PointMass

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int gridSize = scanner.nextInt();
        int numQueries = scanner.nextInt();
        List<PointMass> pts = new ArrayList<PointMass>(numQueries);
        for (int i = 0; i < numQueries; i++) {
            String queryType = scanner.next();
            if (queryType.equals(QUERY_ADD)) {
                pts.add(new PointMass(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextLong()));
            } //if
            else if (queryType.equals(QUERY_CALC)) {
                //Go through all of the point masses that currently exist, check if they are 'within' the location and add them up
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                int z1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();
                int z2 = scanner.nextInt();
                int xB, xT, yB, yT, zB, zT;


                if (x1 <= x2) {
                    xB = x1;
                    xT = x2;
                } //if
                else {
                    xB = x1;
                    xT = x2;
                } //else

                if (y1 <= y2) {
                    yB = y1;
                    yT = y2;
                } //if
                else {
                    yB = y1;
                    yT = y2;
                } //else

                if (z1 <= z2) {
                    zB = z1;
                    zT = z2;
                } //if
                else {
                    zB = z1;
                    zT = z2;
                } //else

                PointMass p1 = new PointMass(xB, yB, zB, 0l);
                PointMass p2 = new PointMass(xT, yT, zT, 0l);

                long massTotal = 0l;

                for (PointMass pt : pts) {
                    if (pt.isWithin(p1, p2)) {
                        massTotal += pt.mass;
                    } //if
                } //for
                System.out.println(massTotal);
            } //else if
            else
                System.out.println("INCORRECT QUERY TYPE.");
        } //for
    } //knightFinder.lineFinder.longestBalancedSubstring.main

} //Solution_PuzzleFinder