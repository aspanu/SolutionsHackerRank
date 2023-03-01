package interviewPrep

// https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem

fun jumpingOnClouds(c: Array<Int>): Int {
    // Array is 0 or 1 based on whether it is 'safe' to jump.
    // Always try to jump '2', and if it is not safe, jump only one
    // Keep track of where we are and how many jumps we've made

    val done = false
    var numJumps = 0
    var currentIndex = 0
    while (!done) {
        if (currentIndex + 2 > c.size) return numJumps // We got to the end!
        if (currentIndex + 2 == c.size) return numJumps + 1 // We are 2 away from the end, but we are trying to get to the last cloud
        currentIndex = if (c[currentIndex + 2] == 0) currentIndex + 2
        else currentIndex + 1
        numJumps++
    }
    return numJumps
}

fun main() {
    println(jumpingOnClouds(arrayOf(0,0,0,1,0,0))) // 3
    println(jumpingOnClouds(arrayOf(0,0,0,0,1,0))) // 3
    println(jumpingOnClouds(arrayOf(0,0,1,0,0,1,0))) // 4
}