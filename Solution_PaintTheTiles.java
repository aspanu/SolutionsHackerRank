import java.util.Scanner;

/**
 * Created by aspanu on 2016-04-10
 * https://www.hackerrank.com/contests/hourrank-7/challenges/tile-painting
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTiles = scanner.nextInt();

    String tiles = scanner.next();

    if (numTiles < 2) {
      System.out.println("0");
      return;
    }

    Character previousTile = tiles.charAt(0);
    int numTransitions = 1; // Start at 1 since we always need a first stroke

    //Go through all of the characters in the tiles and count the number of transitions
    for (int i = 1; i < numTiles; i++) {
      if (previousTile != tiles.charAt(i)) {
        numTransitions++;
      }

      previousTile = tiles.charAt(i);
    }


    System.out.println(numTransitions);
  }

}