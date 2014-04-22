package SolutionsHackerRank;

import java.util.*;

public class Solution_NumberOfDifferences {
    
    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numbers = scanner.nextInt();
        int difference = scanner.nextInt();
        Set<Integer> integerSet = new HashSet<Integer>();

        for (int i = 1; i <= numbers; i++) {
            integerSet.add(scanner.nextInt());
        } //for

        List<Integer> tempList = new ArrayList<Integer>(integerSet);
        Collections.sort(tempList);

        int numPairs = 0;
        for ( Integer member : tempList) {
            if (integerSet.contains(member + difference))
                numPairs++;
        } //for

        System.out.println(numPairs);

    } //main

} //Solution