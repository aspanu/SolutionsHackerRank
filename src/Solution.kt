

/**
 * Created by aspanu on 2017-06-17.
 * The solution for https://www.hackerrank.com/challenges/torque-and-development
 */

fun main(args: Array<String>) {

    val testLine = readLine()!!

    print(testLine)

    // 3 nodes
    // doubly linked nodes
    // 1 2
    // 2 3
    // 3 1
    // if cost_lib < cost_road, build libs in all cities
    // add a library in every city, then add all possible roads, keep track of the current cost and then keep going down
    // this builds a tree, where at every level we are adding a road or a library to the previous thing
    // What we need: store the state of cities (i.e. have library or not), current cost, store the roads possible and roads built?

}

data class City(var hasLibrary: Boolean, val roadsTo: MutableMap<City, Boolean>)