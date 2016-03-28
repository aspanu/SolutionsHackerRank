import com.sun.deploy.util.StringUtils;
import java.util.*;

/**
 * Created by aspanu on 2016-03-28
 * Australian Elections
 * From Skiena and Revilla, Programming Challenges: The Programming Contest Training Manual
 * Springer-Verlag, New York, 2003. ISBN: 0-387-00163-8
 *
 * Used a completely naive solution, I believe there is a better method to this if I use
 * a more complex set of data structures to organize the votes.
 * Something that will prevent me from going through the vote rows each round.
 */

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numElections = scanner.nextInt();

    for (int i = 0; i < numElections; i++) {
      runElection(scanner);
      System.out.println(); // Blank line between elections
    }

  }

  private static void runElection(Scanner scanner) {
    int numCandidates = scanner.nextInt();
    scanner.nextLine(); //Eat a line ending
    List<String> candidates = new ArrayList<>();
    for (int i = 0; i < numCandidates; i++) {
      candidates.add(StringUtils.trimWhitespace(scanner.nextLine()));
    }

    List<List<Integer>> votes = collectVotes(scanner);

    List<Integer> winners = calculateResultsNaive(votes);

    publishResults(candidates, winners);
  }

  private static List<List<Integer>> collectVotes(Scanner scanner) {
    List<List<Integer>> votesOut = new ArrayList<>();
    String nextLine = scanner.nextLine();
    while (!nextLine.isEmpty()) {
      String[] oneVoterString = nextLine.split(" ");
      List<Integer> oneVoter = new ArrayList<>();
      for (String s : oneVoterString) {
        oneVoter.add(Integer.parseInt(s));
      }
      votesOut.add(oneVoter);
      nextLine = scanner.nextLine();
    }
    return votesOut;

  }

  private static List<Integer> calculateResultsNaive(List<List<Integer>> votes) {
    // Each round completely go through the votes array
    int round = 1;
    int numVoters = votes.size();
    int numCandidatesLeft = votes.get(0).size();
    List<Integer> winners = new ArrayList<>();
    List<Boolean> eliminated = new ArrayList<>();
    List<Integer> voteCounts = new ArrayList<>();

    for (int i = 0; i < numCandidatesLeft; i++) {
      eliminated.add(false);
      voteCounts.add(0);
    }

    boolean electionOver = false;
    while (!electionOver) {
      // Distribute votes
      for (List<Integer> voterChoices : votes ) {
        if (otherVotesEliminated(voterChoices, eliminated, round)) {
          int candidateIlike = voterChoices.indexOf(round);
          voteCounts.set(candidateIlike, voteCounts.get(candidateIlike) + 1);
        }
      }

      // Check if we won, if not, eliminate candidates
      int max = -1;
      int min = numVoters + 1;
      List<Integer> maxNumVotes = new ArrayList<>();
      List<Integer> minNumVotes = new ArrayList<>();
      int c = 0; // Index of each tally
      for (Integer voteCount : voteCounts) {
        if (!eliminated.get(c)) {
          if (voteCount > max) {
            max = voteCount;
            maxNumVotes.clear();
          }
          if (voteCount < min) {
            min = voteCount;
            minNumVotes.clear();
          }
          if (voteCount == min) {
            minNumVotes.add(c);
          }
          if (voteCount == max) {
            maxNumVotes.add(c);
          }
        }
        c++;
      }

      if ((max / numVoters > 0.5) || maxNumVotes.size() == numCandidatesLeft) {
        // We have winners!
        winners.addAll(maxNumVotes);
        electionOver = true;
      } else {
        // Eliminate some candidates and then restart
        for (Integer minNumVote : minNumVotes) {
          eliminated.set(minNumVote, true);
          numCandidatesLeft--;
        }
      }

      round++;
    }


    return winners;
  }

  private static boolean otherVotesEliminated(List<Integer> voterChoices, List<Boolean> eliminated, int round) {
    for (int i = 1; i < round; i++) {
      if (!eliminated.get(voterChoices.indexOf(i))) {
        return false;
      }
    }

    // Everyone I previously voted for has been eliminated
    return true;
  }

  private static void publishResults(List<String> candidates, List<Integer> winners) {
    for (Integer winner : winners) {
      System.out.println(candidates.get(winner));
    }
  }




}