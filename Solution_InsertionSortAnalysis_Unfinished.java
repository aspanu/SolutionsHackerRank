import java.util.*;

/**
 * Created by aspanu on 2016-02-19
 * Insertion Sort Advanced Analysis
 * https://www.hackerrank.com/challenges/insertion-sort
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numArrays = scanner.nextInt();

    for (int i = 0; i < numArrays; i++) {
      int arraySize = scanner.nextInt();
      handleArray(scanner, arraySize);
    }

  }

  private static void handleArray(Scanner scanner, int arraySize) {
    int firstNum = scanner.nextInt();
    SortedSet<Integer> inputSet = new TreeSet<>();
    inputSet.add(firstNum);
    long numMoves = 0;
    for (int i = 1; i < arraySize ; i++) { // We start at 1 since we already put in the firstNum
      int nextNumber = scanner.nextInt();
      SortedSet<Integer> tailSet = inputSet.tailSet(nextNumber);
      int toAdd = tailSet.size();
      if (inputSet.contains(nextNumber)) {
        toAdd--; // Since tailSet returns ge the number, and not gt
      }
      numMoves+= toAdd;
      inputSet.add(nextNumber);
    }

    System.out.println(numMoves);
  }
}