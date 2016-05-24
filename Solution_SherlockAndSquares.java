import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aspanu on 2016-05-21
 * https://www.hackerrank.com/challenges/sherlock-and-squares
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numCases = scanner.nextInt();
    ArrayList<Integer> numbers = new ArrayList<>(numCases);
    int biggestNumber = 0;

    for (int i = 0; i < 2 * numCases; i++) {
      int number = scanner.nextInt();
      numbers.add(number);
      if (number > biggestNumber) {
        biggestNumber = number;
      }
    }

    int numSquares = Math.toIntExact(Math.round(Math.ceil(Math.sqrt(biggestNumber))));
    ArrayList<Integer> squares = new ArrayList<>(numSquares + 1); // +1 for zero
    for (int i = 0; i <= numSquares; i++) {
      squares.add(i*i);
    }

    for (int i = 0; i < numbers.size(); i = i + 2) {
      int num1 = numbers.get(i);
      int num2 = numbers.get(i+1);

      System.out.println(numSquaresBetweenNum1Num2(squares, num1, num2));
    }
  }

  private static Integer numSquaresBetweenNum1Num2(ArrayList<Integer> squares, int num1, int num2) {
    int counter = 0;
    for (Integer square : squares) {
      if (square >= num1 && square <= num2) {
        counter++;
      }
      if (square > num2) {
        break;
      }
    }
    return counter;

  }

}