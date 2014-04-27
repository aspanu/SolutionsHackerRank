package SolutionsHackerRank;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_ColourTheGraph {

    public static int treeDepth;

    static class Node {
        public int depth;

        Node(int depth) {
            this.depth = depth;
        }

    } //Node

    public static void main(String args[] ) throws Exception {

        Scanner scanner = new Scanner(System.in);

        int numNodes= scanner.nextInt();
        ArrayList<Node> tree = new ArrayList<Node>(numNodes);
        tree.add(0, new Node(1));
        treeDepth = 1;
        for (int i = 0; i < numNodes-1; i++) {
            int nodeFrom = scanner.nextInt();
            int nodeTo = scanner.nextInt();

            //nodeFrom should always exist and nodeTo should always need to be created.
            tree.add(nodeTo - 1,new Node(tree.get(nodeFrom - 1).depth + 1));
            if (treeDepth < tree.get(nodeTo-1).depth)
                treeDepth = tree.get(nodeTo-1).depth;
        } //for
        System.out.println(treeDepth);
    } //main

} //Solution