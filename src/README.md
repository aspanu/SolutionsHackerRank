# Challenge: Maintain and Traverse a Graph

## Solution:

Here we present a solution for the graph maintenance and traversal problem given below. This solution optimizes for the lookup speed in the graph at the expense of addition and removal operations

#### Runtime Complexity
 * 'add' operations are moderately fast - `O(n)`
    - Potentially all nodes must be iterated through with add additional subgraphs added to them, an operation which should be  
 * 'remove' operations are slow - `O(n*l)`
 * 'isLinked' operations are fast - `O(1)`


#### Space Complexity


#### Assumptions:
- We have no additional information about the frequency of 'add' vs. 'remove' operations, thus we can make arbitrary decisions about which ones are more frequently encountered, the decision was made to penalize 'remove' instructions as a way to gain simplicity of the data model
- 


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