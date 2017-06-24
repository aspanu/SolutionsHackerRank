import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aspanu on 2017-04-17
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median
 */

public class Solution {

  // Solution: Keep a growing list that's always sorted, then take the median each time something new comes in
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> constantlySortedList = new ArrayList<Integer>() {
            public boolean add(Integer mt) {
                int index = Collections.binarySearch(this, mt);
                if (index < 0)
                    index = ~index;
                super.add(index, mt);
                return true;
            }
        };

        for (int i = 0; i < n; i++) {
            int input = scanner.nextInt();
            constantlySortedList.add(input);
            System.out.println(getMedian(constantlySortedList));
        }
    }
    private static double getMedian(List<Integer> list) {
        if (list.size() % 2 == 0) {
            return average(list.get((list.size() / 2) - 1), list.get(list.size() / 2));
        } else {
            return list.get(list.size() / 2); // Integer division gives us the right answer here
        }
    }

    private static double average(Integer a, Integer b) {
        return (a + b) / 2.0;
    }
}