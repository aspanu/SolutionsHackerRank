import java.util.*;

/**
 * Created by aspanu on 2016-01-20
 * 101 Hack January 2016
 * https://www.hackerrank.com/contests/101hack33/challenges/max-sum-subarray
 */


public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int arraySize = scanner.nextInt();
    long maxSum = 0;
    long currSum = 0;

    for (int i = 0; i < arraySize; i++) {
      // Take in integers one at a time, add them to the current sum, if larger than the max sum, update that
      long currInt = scanner.nextLong();

      if (currInt != 0) {
        currSum += currInt;
      } else {
        currSum = 0;
      }

      if (maxSum == 0 || currSum > maxSum) {
        maxSum = currSum;
      }

    }

    if (maxSum < 0) {
      maxSum = 0;
    }

    System.out.println(maxSum);

  }
}