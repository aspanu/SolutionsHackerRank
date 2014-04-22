package SolutionsHackerRank;

import java.util.Scanner;

public class Solution_InfiniteRectangle {

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numLines = scanner.nextInt();

        int minX = 1000000; //The largest number possible
        int minY = 1000000;

        for (int i = 0; i < numLines; i++) {
            int coordX = scanner.nextInt();
            int coordY = scanner.nextInt();

            if (coordX < minX)
                minX = coordX;

            if (coordY < minY)
                minY = coordY;
        } //for

        System.out.println(((long) minX) * minY);
    } //main

} //Solution