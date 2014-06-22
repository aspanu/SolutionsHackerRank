package SolutionsHackerRank;

/**
 * Solution_PuzzleFinder for ColoringTree problem from 101 Hack February on www.hackerrank.com
 * Adrian Spânu
 * 2014-04-01
 */

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

Solution_PuzzleFinder by Alex Chow. My solution is below.

public class Solution_PuzzleFinder {

    static class Node {
        Set<Node> children = new LinkedHashSet<Node>();
        Integer color;
        Set<Integer> subtreeUniqueColors = null; // memoize.
        Set<Integer> getSubtreeUniqueColors() {
            if (subtreeUniqueColors != null) {
                return subtreeUniqueColors;
            }
            LinkedHashSet<Integer> subtreeUniqueColors = new LinkedHashSet<Integer>();
            for (Node child : children) {
                subtreeUniqueColors.addAll(child.getSubtreeUniqueColors());
            }
            subtreeUniqueColors.add(color);
            this.subtreeUniqueColors = subtreeUniqueColors;
            return this.subtreeUniqueColors;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
        }

        for (Integer result : compute(stringBuilder.toString())) {
            System.out.println(result);
        }
    }

    private static ArrayList<Integer> compute(String input) {
        String[] lines = input.split("\\n");
        int i = 0;
        String[] config = lines[0].split(" ");
        int numNodes = Integer.parseInt(config[0]);
        int numQueries = Integer.parseInt(config[1]);
        int root = Integer.parseInt(config[2]);
        ArrayList<Node> nodes = buildTree(numNodes, numQueries, root, lines);
        ArrayList<Integer> colorCounts = getColorCounts(nodes, numNodes, numQueries, lines);
        return colorCounts;
    }

    private static ArrayList<Integer> getColorCounts(
            ArrayList<Node> nodes,
            int numNodes,
            int numQueries,
            String[] inputLines) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // Query starts at line 2*numNodes.
        for (int i = 0; i < numQueries; i++) {
            Integer queryNode = Integer.parseInt(inputLines[2*numNodes + i]);
            result.add(nodes.get(queryNode - 1).getSubtreeUniqueColors().size());
        }
        return result;
    }

    private static ArrayList<Node> buildTree(
            int numNodes,
            int numQueries,
            int root,
            String[] inputLines) {
        ArrayList<Node> nodes = new ArrayList<Node>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node());
        }

        // Make edges. N-1 edges.
        for (int i = 1; i < numNodes; i++) {
            String[] line = inputLines[i].split(" ");
            nodes.get(Integer.parseInt(line[0]) - 1).children.add(nodes.get(Integer.parseInt(line[1]) - 1));
        }

        // Add colors.
        for (int i = 0; i < numNodes; i++) {
            Integer color = Integer.parseInt(inputLines[numNodes + i]);
            nodes.get(i).color = color;
        }

        return nodes;
    }
}
*/
/*
import java.util.*;


public class Solution_ColouringTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numNodes = scanner.nextInt();
        int numQueries = scanner.nextInt();
        int treeRoot = scanner.nextInt();

        ArrayList<Node> tree = new ArrayList<Node>();

        for (int i = 0; i < numNodes; i++) {
            tree.add(new Node());
        }

        for (int i = 0; i < numNodes - 1; i++) {
            int nodeFrom = scanner.nextInt();
            int nodeTo = scanner.nextInt();
            tree.get(nodeFrom - 1).children.add(tree.get(nodeTo - 1));
        } //for

        //Build the list of nodes in terms of their 'colours' and add it to the HashMap as well
        for (int i = 0; i < numNodes; i++) {
            tree.get(i).colour = scanner.nextInt();
        } //for

        //Navigate the tree once and populate colourUnderNode with the size of the tree at each point

        for (int i = 0; i < numQueries; i++) {
            int nextQuery = scanner.nextInt();
            System.out.println(tree.get(nextQuery - 1).getSubtreeUniqueColours().size());
        } //for

    } //main

} //Solution_PuzzleFinder

/*
class Node {
    Integer colour;
    Set<Node> children = new LinkedHashSet<Node>();
    Set<Integer> subtreeUniqueColours = null;


    public Node() {
        colour = 0;
    }

    Set<Integer> getSubtreeUniqueColours() {
        if (subtreeUniqueColours != null)
            return subtreeUniqueColours;

        LinkedHashSet<Integer> subtreeUniqueColours = new LinkedHashSet<Integer>();
        for (Node child : children) {
            subtreeUniqueColours.addAll(child.getSubtreeUniqueColours());
        } //for
        subtreeUniqueColours.add(colour);

        this.subtreeUniqueColours = subtreeUniqueColours;

        return this.subtreeUniqueColours;
    } //getSubtreeUniqueColours
} //Node
*/