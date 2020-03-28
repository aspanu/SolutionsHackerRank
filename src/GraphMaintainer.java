import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The GraphMaintainer contains all of the logic for the storage and maintenance of a graph
 * The graph representation itself is made to be as minimal as possible for the requirements
 * We do not store any structure of the graph beyond which nodes are connected to other nodes, including themselves
 * This means that 'removal' operations force us to recreate the entire history of the graph,
 * requiring us to store that history
 *
 * This architecture implies that:
 * 'add' operations are moderately fast [O(n)],
 * 'remove' operations are slow [O(n*l)]
 * 'isLinked' operations are fast [O(1)]
 *
 */
public class GraphMaintainer {

    private Set<AddInstruction> history;
    private HashMap<Long, HashSet<Long>> graph;

    public GraphMaintainer() {
        history = new HashSet<>();
        graph = new HashMap<>();
    }

    public void add(AddInstruction instruction) {
        history.add(instruction);
        processAdd(instruction);
    }

    /**
     * Get the left and right subGraphs, add all of the nodes of the other subgraph to the common set
     * of the larger subgraph and then update the smaller subgraph to point to the common set
     *
     * This assumes that we are joining two distinct subgraphs each operation.
     */
    private void processAdd(AddInstruction instruction) {
        initializeNewNodes(instruction);

        Set<Long> leftSubGraph = graph.get(instruction.getLeft());
        Set<Long> rightSubGraph = graph.get(instruction.getRight());

        if (rightSubGraph.contains(leftSubGraph.iterator().next())) {
            // The two subgraphs should already be the same, no need to update anything
            return;
        }

        if (leftSubGraph.size() < rightSubGraph.size()) {
            addASubGraphToAllNodesOfASubGraph(leftSubGraph, instruction.getRight());
        } else {
            addASubGraphToAllNodesOfASubGraph(rightSubGraph, instruction.getLeft());
        }
    }

    private void initializeNewNodes(AddInstruction instruction) {
        if (!graph.containsKey(instruction.getLeft())) {
            graph.put(instruction.getLeft(), new HashSet<>(Arrays.asList(instruction.getLeft())));
        }
        if (!graph.containsKey(instruction.getRight())) {
            graph.put(instruction.getRight(), new HashSet<>(Arrays.asList(instruction.getRight())));
        }
    }

    private void addASubGraphToAllNodesOfASubGraph(Set<Long> nodes, Long otherNode) {
        graph.get(otherNode).addAll(nodes); // Updating the common set

        // Update the nodes to point to the common set
        for (Long node : nodes) {
            graph.put(node, graph.get(otherNode));
        }
    }

    /**
     * If this link doesn't exist, then removing it won't do anything, so we can short circuit
     */
    public void remove(AddInstruction instruction) {
        if (!history.contains(instruction)) {
            return;
        }
        history.remove(instruction);
        // Now reset the graph and start again
        graph = new HashMap<>();
        for (AddInstruction ai : history) {
            processAdd(ai);
        }
    }

    /**
     * To avoid errors, we check if both of the nodes are in the graph before we check if
     */
    public boolean isLinked(Long node1, Long node2) {
        if (graph.containsKey(node1) && graph.containsKey(node2)) {
            return graph.get(node1).contains(node2);
        }
        return false;
    }

    public Set<AddInstruction> getHistory() {
        return history;
    }

    public HashMap<Long, HashSet<Long>> getGraph() {
        return graph;
    }
}

/**
 * Data class containing the information from an instruction for adding something to the graph
 * This is maintained so that the left and right nodes being joined can be sorted on construct giving
 * us stability in the equals and hashcode methods, letting us use AddInstructions safely as keys in Sets
 *
 * I pulled it out as a non-private class so I could do a bit more testing on it as a unit by itself.
 * This is a semi-questionable decision and is up to interpretation. Happy to put it as a private class as it is
 * mostly an implementation detail.
 *
 * God how I wish I could write this in Kotlin
 */
class AddInstruction {
    private Long left;
    private Long right;

    public AddInstruction(Long firstNumber, Long secondNumber) {
        this.left = Math.min(firstNumber, secondNumber);
        this.right = Math.max(firstNumber, secondNumber);
    }

    public Long getLeft() {
        return left;
    }

    public Long getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddInstruction that = (AddInstruction) o;

        if (!left.equals(that.left)) return false;
        return right.equals(that.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
