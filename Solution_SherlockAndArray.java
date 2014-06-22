package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aspanu on 6/13/14.
 */

public class Solution_SherlockAndArray {


    public static void main(String args[] ) {

        Scanner scanner = new Scanner(System.in);

        int numTests = scanner.nextInt();

        for (int i = 0; i < numTests; i++) {
            int numInArray = scanner.nextInt();
            int rightSum = 0;
            int leftSum = 0;
            boolean flag = false;
            ArrayList<Integer> numbers = new ArrayList<Integer>(numInArray);
            for (int j = 0; j < numInArray; j++) {
                int nextNum = scanner.nextInt();
                rightSum = rightSum + nextNum;
                numbers.add(nextNum);
            } //for

            int toKeep = numbers.get(0);
            rightSum = rightSum - toKeep;

            if (numInArray == 1)
                flag = true;

            for (int j = 1; j < numInArray; j++ ) {
                if (leftSum == rightSum) {
                    flag = true;
                    break;
                }
                leftSum = leftSum + toKeep;
                rightSum = rightSum - numbers.get(j);
                toKeep = numbers.get(j);
            } //for

            if (flag)
                System.out.println("YES");
            else
                System.out.println("NO");
        } //for

    } //main
}
