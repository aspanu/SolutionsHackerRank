package interviewPrep

// https://www.hackerrank.com/challenges/repeated-string/problem

fun repeatedString(s: String, n: Long): Long {
    // Check to see how many 'a's there are in the substring
    // Figure out how many times the substring fits into the number of values,
    // then for the last one, figure out how many 'a's in the substring "left"

    val numAsPerString = getNumAs(s)
    val numStringsInRepeats = n / s.length

    val numWholeStringAs = numStringsInRepeats * numAsPerString

    val partialStringSize = n.rem(s.length)
    val numAsPerPartialString = getNumAs(s.substring(0,partialStringSize.toInt()))

    return numWholeStringAs + numAsPerPartialString
}

fun getNumAs(s: String): Long {
    var numAs = 0L
    s.forEach { if (it == 'a') numAs++ }

    return numAs
}

fun main() {
    println(repeatedString("aaaba",32)) // 26
    println(repeatedString("aba",10)) // 7
    println(repeatedString("a",1000000000000)) // 1000000000000
}