package SolutionsHackerRank;

import java.util.Scanner;

public class Solution_FactorialFactorization {

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int bigN = scanner.nextInt();

        int numFactors = factorialFactors(bigN);

        if (bigN != 1)
            numFactors = numFactors * 2; //Since we are actually looking for N! ^ 2 not N!)
        System.out.println(numFactors);

    } //main

    private static int factorize(int toFactorize) {

        if (toFactorize == 1)
            return 1;

        int count = 0;
        long number = toFactorize;
        for (int i = 2; i<=(number); i++) {
            while (number % i == 0) {
                number /= i;
                count++;
            } //while
        } //for

        return count;
    }

    private static int factorialFactors(int toFactorial) {
        int totalCount = 0;
        for (int i = toFactorial; i > 0; i--) {
            totalCount = totalCount + factorize(i);
        }
        return totalCount;
    }

} //Solution