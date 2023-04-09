package interviewPrep
//
//import kotlin.math.round
//
//// For a 2D matrix:
////
//// 1, 3, 4, 10,
//// 2, 5, 9, 11,
//// 6, 8, 12, 15,
//// 7, 13, 14, 16
////
//// Unzip the matrix so you get back this output array:
//// 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
//
//// at a specific location, move either 'down' or 'up'
//// if going down, increase the row and decrease the column by 1 each
//// if going up, increase the col and decrease the row by 1 each
//// if at a wall, and going down, only increase the row or col (depending on the wall)
//// if at a wall, and going up, only increase the row or col (depending on the wall)
//// - regardless of direction, is hitting a wall, always increment whatever is possible and switch direction
//
//// make this recursive?
//// base case: at 2 walls and cannot move any further, return path taken so far
//
//// A combination of location and direction
//data class Velocity(val row: Int, val col: Int, val isDown: Boolean)
//fun makeMove(matrix: List<List<Int>>, currentVelocity: Velocity, visitedSoFar: MutableList<Int>): List<Int> {
//    // Base case
//    if (isAtLastPoint(matrix, currentVelocity)) {
//        return visitedSoFar
//    }
//    // Find the next point, and then add it to visited so far
//    val nextVelocity = getNextVelocity(matrix, currentVelocity)
//    visitedSoFar.add(matrix[nextVelocity.row][nextVelocity.col])
//
//    makeMove(matrix, nextVelocity, visitedSoFar)
//    return visitedSoFar
//}
//
//fun getNextVelocity(matrix: List<List<Int>>, currentVelocity: Velocity): Velocity {
//    // Given a specific velocity, find the next one, according to these rules:
//    // if going down, increase the row and decrease the column by 1 each
//    // if going up, increase the col and decrease the row by 1 each
//    // if at a side edge, only increase the row (depending on the wall)
//    // if at a top/bottom, only increase the col (depending on the wall)
//    // - regardless of direction, is hitting a wall, always increment whatever is possible and switch direction
//
//    val testNextVelocity = if (currentVelocity.isDown) Velocity(currentVelocity.row + 1, currentVelocity.col - 1, currentVelocity.isDown)
//    else Velocity(currentVelocity.row - 1, currentVelocity.col + 1, currentVelocity.isDown)
//
//    val actualNextVelocity = if (isOutsideSideEdges(matrix, testNextVelocity))
//        else if (isOutsideTopBottom(matrix, testNextVelocity)) Velocity
//    TODO("Not yet implemented")
//}
//
//fun isAtLastPoint(matrix: List<List<Int>>, currentVelocity: Velocity): Boolean {
//    return (currentVelocity.row == (matrix.size - 1)) && (currentVelocity.col == (matrix[0].size - 1))
//}
