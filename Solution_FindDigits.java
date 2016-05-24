import java.util.Scanner;

/**
 * Created by aspanu on 2016-05-21
 * https://www.hackerrank.com/challenges/find-digits
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numCases = scanner.nextInt();

    for (int i = 0; i < numCases; i++) {
      int number = scanner.nextInt();
      String numberRepresentation = Integer.toString(number);
      int count = 0;

      for (int j = 0; j < numberRepresentation.length(); j++) {
        int digit = Integer.parseInt(String.valueOf(numberRepresentation.charAt(j)));
        if (digit == 0) {
          continue;
        }
        if (number % digit == 0) {
          count++;
        }
      }

      System.out.println(count);
    }

  }

}