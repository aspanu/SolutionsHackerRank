package lineFinder

/**
 * Created by aspanu on 2017-06-24 - written in Kotlin
 */

/**
 * Problem:
 * Write a function that takes a list of points in 2D space as an input, and returns a list of lines that have more than 2 input points on them. That is:
 * You have a list of points [(x0, y0), ..., (xK, yK)]
 * From the basic geometry, you can always draw a straight line through any pair of points. If such line has more than 2 points from the input list on it, it needs to be included in the result.
 * Test example:
 * test_example = [(0., 0.), (1., 1.), (3., 4.), (2., 2.)]
 * In this example, the points (0,0), (1,1), and (2,2) are all on the same line. Your function should return a list that will contain this line as its only element. All other lines you can make from test_example (e.g. (0,0), (3,4)) will only have two points.
 *
 * Assumptions:
 * 1. Points are in integer space
 * 2. We are comfortable with infinite slopes (as we should be with lines)
 *
 * Discussion:
 * 1. Lines are defined as a slope (m) and an intercept (b) which are the co-efficients of a line as per y = m*x + b
 * 2. Efficiency isn't amazing, we are currently doing an O(n^2) algorithm of go through every point and compare to every other point
 *      Future work of making it better, but fundamentally, the algorithm will continue to be O(n^2)
 *      a) remove lines which were already found in the set in the 'groupBy' step rather than the 'addAll' step
 *      b) Use a set to filter out the 'focusPoint' to skip out on the O(n) search each iteration
 * 3. I wrote this in Kotlin
 *
 */

data class Line (val m : Double, val b : Double)

data class Point(val x: Double, val y: Double)


fun main(args: Array<String>) {
//    println(lineFinder.getLinesFromPoints(listOf(lineFinder.Point(x = 0.0, y = 0.0), lineFinder.Point(x = 1.0, y = 1.0), lineFinder.Point(x = 3.0, y = 4.0), lineFinder.Point(x = 2.0, y = 2.0))))
//    println(lineFinder.getLinesFromPoints(listOf(lineFinder.Point(x = 0.0, y = 0.0), lineFinder.Point(x = 1.0, y = 1.0), lineFinder.Point(x = 3.0, y = 4.0), lineFinder.Point(x = 2.0, y = 2.0), lineFinder.Point(x = 0.0, y = 1.0), lineFinder.Point(x = 1.0, y = 2.0), lineFinder.Point(x = 2.0, y = 3.0))))
//    println(lineFinder.getLinesFromPoints(listOf(lineFinder.Point(x = 0.0, y = 0.0), lineFinder.Point(x = 1.0, y = 1.0), lineFinder.Point(x = 3.0, y = 4.0), lineFinder.Point(x = 2.0, y = 2.0), lineFinder.Point(x = 0.0, y = 1.0), lineFinder.Point(x = 1.0, y = 2.0), lineFinder.Point(x = 2.0, y = 3.0), lineFinder.Point(x = 0.0, y = -1.0), lineFinder.Point(x = 1.0, y = -2.0), lineFinder.Point(x = 2.0, y = -3.0))))

    val str = readLine()!!
    for (i in 0..str.toInt() - 1) {
        println("Hello")
    }
}

fun getLinesFromPoints(points : List<Point>) : Set<Line> {
    // Have a set of points, must find all points that are on a line
    // Take one point at a time and bucket things by slope and intercept

    val finalSet = mutableSetOf<Line>()

    for (focusPoint in points) {
        finalSet.addAll(
            points.filter { it != focusPoint }
                .groupBy { val m = (focusPoint.y - it.y)/(focusPoint.x - it.x); Line(m = m, b = (it.y - m * it.x)) }
                .filter { it.value.size > 1 }.keys
        )
    }

    return finalSet
}