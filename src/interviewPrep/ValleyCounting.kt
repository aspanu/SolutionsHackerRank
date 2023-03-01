package interviewPrep

// Count the number of valleys below sea level
// https://www.hackerrank.com/challenges/counting-valleys/problem
fun countingValleys(steps: Int, path: String): Int {
    // What we really want to do is count the number of -1s after a '0'
    // So let's do it dead simple - turn the string into a path of the current elevation, then take another pass
    // keeping track of the number of -1s after a 0

    val elevationPath = mutableListOf<Int>()
    var currentElevation = 0

    for (c in path) {
        currentElevation += convertDirectionToElevationChange(c)
        elevationPath.add(currentElevation)
    }

    var numValleys = 0
    var prev = 0
    var cur: Int
    for (elevation in elevationPath) {
        cur = elevation
        if (prev == 0 && cur == -1) {
            numValleys++
        }
        prev = cur
    }

    return numValleys
}

private fun convertDirectionToElevationChange(c: Char): Int {
    return if (c == 'U') 1
    else -1
}

fun main() {
    println(countingValleys(0, "UDDDUDUU"))
    println(countingValleys(0, "DDUUUUDD"))
}