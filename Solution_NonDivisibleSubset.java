import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aspanu on 2016-05-24
 * https://www.hackerrank.com/contests/w20/challenges/non-divisible-subset
 */

public class Solution {

  // Solution: make a list of frequency of the mod of each number, then add up half of them
  // by splitting them up into pairs that add up to k and taking the pairing which has a larger frequency
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int k = scanner.nextInt();
    ArrayList<Integer> setOfNumbers = new ArrayList<>(k);

    // Initialize the list
    for (int i = 0; i < k; i++) {
      setOfNumbers.add(0);
    }

    // Get the frequency of mods
    for (int i = 0; i < n; i++) {
      int numIn = scanner.nextInt();
      int numInModded = numIn % k;
      setOfNumbers.set(numInModded, setOfNumbers.get(numInModded) + 1);
    }

    int setSize = 0;
    for (int i = 1; i <= (k / 2); i++) {
      int lowerHalf = setOfNumbers.get(i);
      int upperHalf = setOfNumbers.get(k - i);

      int toAdd = 0;
      if (k == (i * 2)) { // Have to deal with the cases where k is an even number
        if (setOfNumbers.get(i) >= 1) {
          toAdd = 1;
        }
      } else {
        if (upperHalf >= lowerHalf) {
          toAdd = upperHalf;
        } else {
          toAdd = lowerHalf;
        }
      }

      setSize = setSize + toAdd;
    }

    if (setOfNumbers.get(0) >= 1) { // All cases where we have multiples of k, we can keep 1 of those
      setSize++;
    }

    System.out.println(setSize);
  }

}