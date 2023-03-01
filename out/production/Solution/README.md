# Challenge: Maintain and Traverse a Graph

## Solution:

Here we present a solution for the graph maintenance and traversal problem given below. This solution optimizes for the lookup speed in the graph at the expense of addition and removal operations, especially against removal operations. The complexities are described in detail below. Please note that 'n' is the number of nodes present in the graph and 'l' is the number of links.

#### Runtime Complexity
 - 'add' operations are moderately fast - `O(n)`
    - In the worst case, an 'add' causes us to need to go through half of all current nodes and update their set to point to a common set
 - 'remove' operations are slow - `O(n*l)`
    - Every 'true' add operation (i.e. an add operation which has not been subsequently removed) must be run again
 - 'isLinked' operations are fast - `O(1)`
    - Each node stores enough information to know which other node it is linked to, so lookups are constant time

#### Space Complexity
Our storage complexity should be on the order of O(2n), which is really a linear complexity of O(n), but we can be somewhat more precise in this case. We must store every node twice (once as a key and once as part of some common set). When we merge subgraphs, we merge sets, but keep the overall content the same. For example, when we merge to subgraphs of sizes 3 and 4, instead of having a set of size 4 and a separate set of size 3, we instead have a set of size 7. Assuming negligible overhead in the storage of a set, all we are doing is moving data around to a new set but keeping the amount of data that we store. The difference between worst case (no nodes are linked, n different subgraphs exist) and the best case (all nodes are linked, 1 subgraph exists) is in the overhead of storage for n sets.

#### Maintainability
Each of the subcomponents has been split out and made modular. The GraphMaintainer keeps an in-memory representation of the graph. Persistence strategies can be added to this component without any changes to its API and without any changes necessary to the Parser. Similarly, the Parser, which maintains the ability to read instructions from arbitrary strings, can be updated without any changes necessary to the GraphMaintainer. As the API is very decoupled between the two subsystems, it is trivial to move the GraphMaintainer to a separate server or perhaps even add an RPC interface between them. (I remember how much you like SOAP, so perhaps that? :) ). Finally, additions to the set of instructions can be made easily and in a decoupled way. The GraphMaintainer can be updated to support the new features before they are actually exposed through the Parser, the update of which can be done asynchronously.

#### Assumptions:
- We have no additional information about the frequency of 'add' vs. 'remove' operations, thus we can make arbitrary decisions about which ones are more frequently encountered, the decision was made to penalize 'remove' instructions as a way to gain simplicity of the data model
- We can be very stringent in our criteria for what kind of instructions we allow.
- "Large integers" as defined in the problem statement will fit inside of a 'Long' type in Java, which tops out at 9,223,372,036,854,775,807


## Problem Statement:

Our app tracks usage events and associates those events with an email address, but sometimes more than one email address can belong to one person. We need to maintain a graph of nodes (where each node represents an email) that can be linked together, unlinked, and queried.

The input is a text file where each line represents a command. The possibilities are:

`add x y` - connect nodes (aka emails) x and y. This is a bidirectional link: if x is connected to y, then y is also connected to x.
`remove x y` - disconnect x and y (also bidirectional: x is not connected to y and y is not connected to x).
`is linked x y` - return whether there is any path between node x and node y

For example:

```
add 1 2
random line, shouldn't cause program to fail
add 2 3
add 3 4
is linked 3 1
remove 3 4
is linked 1 4
```

The output should be:

```
true
false
```

The “true” because there is a path between 1 and 3 (1 is linked to 2, and 2 is linked to 3)

The “false” because there is no path between 1 and 4.

Some more information:

- The graph will be sparsely populated. That is, the number of emails >> the number of links between emails
- We are interested in querying “is linked” much more often than we will add or remove a link. The run time of “is linked” should be constant, but it is ok if add and remove link are more expensive.

**Include in Comments:**

- *What is the memory footprint (also known as space complexity) for your approach in terms of n (# of nodes) and l (# of links).*
- *What is the running time for each of add link, remove link, and is linked?*