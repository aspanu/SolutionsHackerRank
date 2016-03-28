import java.util.*;

/**
 * Created by aspanu on 2016-03-19
 * Save the Prisoner
 * https://www.hackerrank.com/contests/101hack35/challenges/save-the-prisoner
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTimes = scanner.nextInt();

    for (int i = 0; i < numTimes; i++) {
      int numPrisoners = scanner.nextInt();
      int numSweets = scanner.nextInt();
      int numStart = scanner.nextInt();

      if (numSweets > numPrisoners) {
        numSweets = numSweets % numPrisoners;
      }

      int stopAt = numSweets + numStart - 1;

      if (stopAt > numPrisoners) {
        stopAt = stopAt % numPrisoners;
      }

      if (stopAt == 0) {
        stopAt = numPrisoners; // Since this is a 1 based array and not a zero based array
      }
      System.out.println(stopAt);

    }
  }

}