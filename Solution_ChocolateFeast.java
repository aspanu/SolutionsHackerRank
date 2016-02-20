import java.util.*;

/**
 * Created by aspanu on 2016-02-20
 * Chocolate Feast
 * https://www.hackerrank.com/challenges/chocolate-feast
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numCases = scanner.nextInt();

    for (int i = 0; i < numCases; i++) {
      int amtOfMoney = scanner.nextInt();
      int pricePerChocolate = scanner.nextInt();
      int numWrappersToTurnIn = scanner.nextInt();
      handleCalculations(amtOfMoney, pricePerChocolate, numWrappersToTurnIn);
    }

  }

  private static void handleCalculations(int amtOfMoney, int pricePerChocolate, int numWrappersToTurnIn) {
    int numChocolatesEaten = 0;

    int numChocsToEatRightNow = amtOfMoney / pricePerChocolate;
    numChocolatesEaten += numChocsToEatRightNow;
    int numWrappersRemaining = 0;
    boolean letsExit = false;

    while (!letsExit) {
      if ((numChocsToEatRightNow + numWrappersRemaining) < numWrappersToTurnIn) {
        letsExit = true;
      } else {
        int numChocsToEatRightNowTemp = (numChocsToEatRightNow + numWrappersRemaining) / numWrappersToTurnIn;
        numWrappersRemaining = (numChocsToEatRightNow + numWrappersRemaining) % numWrappersToTurnIn;
        numChocolatesEaten += numChocsToEatRightNowTemp;
        numChocsToEatRightNow = numChocsToEatRightNowTemp;
      }
    }

    System.out.println(numChocolatesEaten);
  }

}