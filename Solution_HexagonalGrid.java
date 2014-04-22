package SolutionsHackerRank;

import java.util.Scanner;

public class Solution_HexagonalGrid {

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int tests = Integer.parseInt(scanner.nextLine());
        scanner.nextLine();
        for (int i = 0; i < tests; i++) {
            int size = Integer.parseInt(scanner.nextLine());
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            int count = 0;
            System.out.println("Line 1 = " + line1);
            System.out.println("Line 2 = " + line2);
            for (int j = 0; j < size; j++) {
                if (Integer.parseInt(String.valueOf(line1.charAt(j))) == 0)
                    count++;
                if (Integer.parseInt(String.valueOf(line2.charAt(j))) == 0)
                    count++;
            } //for

            if (count % 2 == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        } //for

    } //main

} //Solution