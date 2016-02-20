import java.util.*;

/**
 * Created by aspanu on 2016-02-20
 * Milo's Diary
 * https://www.hackerrank.com/contests/booking-hack-a-holiday/challenges/milos-diary
 */

// Get a number, check to see if it is larger than the last number or 1 smaller than the last number, update the last
// number and get another number.
// Other things: check to see if it's already been used

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numNumbers = scanner.nextInt();
    boolean incorrectDiary = false;
    int lastNumber = scanner.nextInt();
    if (numNumbers < 1) {
      // Exit early if we have no numbers or we have only 1, in both cases, this is a correct diary
      System.out.println("YES");
      return;
    }

    HashSet<Integer> numberSet = new HashSet<>();
    numberSet.add(lastNumber);

    for (int i = 1; i < numNumbers; i++) {
      // Start at 1 since we already took one number above to be the lastNumber
      int nextNumber = scanner.nextInt();

      if (!numberSet.contains(nextNumber + 1) && nextNumber <= lastNumber) {
        incorrectDiary = true;
        break;
      }

      if (nextNumber < lastNumber && !numberSetCheck(numberSet, nextNumber, lastNumber)) {
        incorrectDiary = true;
        break;
      }

      if (numberSet.contains(nextNumber)) {
        incorrectDiary = true;
        break;
      }
      numberSet.add(nextNumber);
      lastNumber = nextNumber;
    }

    if (incorrectDiary) {
      System.out.println("NO");
    } else {
      System.out.println("YES");
    }

  }

  private static boolean numberSetCheck(HashSet<Integer> numberSet, int nextNumber, int lastNumber) {
    // Check that all numbers between nextNumber and lastNumber are filled in to the set
    for (int i = nextNumber + 1; i < lastNumber; i++) {
      if (!numberSet.contains(i)) {
        return false;
      }
    }
    return true;
  }

}