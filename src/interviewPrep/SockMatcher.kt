package interviewPrep

fun sockMerchant(n: Int, ar: Array<Int>): Int {
    val socksByColour = mutableSetOf<Int>()
    var sockPairNumber = 0

    ar.forEach {
        if (socksByColour.contains(it)) {
            sockPairNumber++
            socksByColour.remove(it)
        } else {
            socksByColour.add(it)
        }
    }

    return sockPairNumber
}

fun main() {
    println(sockMerchant(0, arrayOf(1,2,1,2,1,3,2)))
    println(sockMerchant(0, arrayOf(10, 20, 20, 10, 10, 30, 50, 10, 20)))
}