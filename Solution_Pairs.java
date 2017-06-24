import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by aspanu on 2017-04-17
 * https://www.hackerrank.com/challenges/pairs
 */

public class Solution {

  // Solution: Create a set from the inputs. Start at the beginning of the set and
  // find groups of integers 'K' apart. Remove them and keep looking before moving on to the next item in
    // the set.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Set<Integer> inputSet = new HashSet<>(n);
        Set<Integer> setToModify = new HashSet<>(n);

        for (int i = 0; i < n; i++) {
            int input = scanner.nextInt();
            inputSet.add(input);
            setToModify.add(input); // This is necessary to avoid ConcurrentModificationException
        }

        int numPairs = 0;

        // This is a O(n) solution!
        for (Integer input : inputSet) {
            if (!setToModify.contains(input)) {
                continue;
            }
            setToModify.remove(input);
            Integer below = input - k;
            while (setToModify.contains(below)) {
                numPairs++;
                setToModify.remove(below);
                below = below - k;
            }
            Integer above = input + k;
            while (setToModify.contains(above)) {
                numPairs++;
                setToModify.remove(above);
                above = above + k;
            }
        }

        System.out.println(numPairs);
    }
}