package mit6006courseProblems

// https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/mit6_006s20_ps3-questions/

// 3-1
// a) A = [47, 61, 36, 52, 56, 33, 92]

// 0 -> [36,92]
// 4 -> [56]
// 5 -> [47, 61, 33]
// 6 -> [52]

// b) 13 -> see code below

// 3-4
// a) Set up a sliding window - which is an ordered set. As you add a new value 'a', check to see r-a is inside the set
// If it is, return true, if not, then add the next item from B. Once the set has a size > n/10, start dropping values
// Each time you add a new one
// b) I'm not sure why r < n^2 matters. R is the number of reams necessary, n is the number of boxes, they don't seem
// to be related in the solution presented above. The above algorithm should be sufficient for this case too.

// 3-5
// a) A map that maps a map of letter counts to frequency to the frequency they occur in every 'k' sub-word. You can
// construct each inner map by taking a sliding window of length 'k' on string A and counting the frequency of
// each letter in that. Then you can count the number of frequencies that that map has across all of the substrings of
// length k in the word A. When you get string B, you figure out its character substring frequency and then call into the
// map you have constructed to see how many of those character frequency maps you have

// b) Do the thing from the above, but now go through the strings in S one at a time and build the frequency
// array for each of them.

// c) Let's do this.


fun hashFunction(k: Int): Int {
    return (10*k + 4) % 7
}

fun hashFunctionB(k: Int, c: Int): Int {
    return ((10 * k + 4) % c) % 7
}

fun main() {
    val prob31a = listOf(47, 61, 36, 52, 56, 33, 92)
    for (k in prob31a) {
        println("$k maps to ${hashFunction(k)}")
    }
    testForC()

    assert(check(tests[0]))
    assert(check(tests[1]))
    assert(check(tests[2]))
    assert(check(tests[3]))
    assert(check(tests[4]))

    for (i in 0..4) {
        if (!check(tests[i])) {
            println("Something is wrong with test $i !!!!!!")
        }
    }
}

fun testForC() {
    val prob31b = listOf(47, 61, 36, 52, 56, 33, 92)
    for (c in 1..30) {
        val hashesSet = mutableSetOf<Int>()
        for (k in prob31b) {
            val hash = hashFunctionB(k, c)
//            println("For k: $k and c: $c got hash: $hash")
            hashesSet.add(hash)
        }
//        println("Size of the hash set: ${hashesSet.size}")
        if (hashesSet.size == 7) println("Found a good c: $c")
    }
}

fun countAnagramSubstrings(originalWordT: String, listOfStringsToTestS: Array<String>): Array<Int> {
    // Create a map of frequencies of size 'k' (which is the size of the words in the array)
    // Then use the map on each string

    val mapOfMapsOfFreqs = createMapForWord(originalWordT, listOfStringsToTestS[0].length)

    val response = mutableListOf<Int>()
    for (testString in listOfStringsToTestS) {
        response.add(getNumberOfItemsForWord(testString, mapOfMapsOfFreqs))
    }

    return response.toTypedArray()
}

fun getNumberOfItemsForWord(word: String, mapOfMapsOfFreqs: Map<Map<Char, Int>, Int>): Int {
    val mapOfFreqs = getMapForWord(word)
    return mapOfMapsOfFreqs[mapOfFreqs] ?: 0
}

fun getMapForWord(word: String): Map<Char, Int> {
    val freqMap = mutableMapOf<Char, Int>()
    for (c in word) {
        freqMap[c] = 1 + (freqMap[c] ?: 0)
    }
    return freqMap
}

fun createMapForWord(t: String, k: Int): Map<Map<Char, Int>, Int> {
    // Create a sliding window, and for each word in the sliding window, get the map for it
    var slidingWindowWord = t.subSequence(0, k).toString()
    val mapOfMapsOfFreqs = mutableMapOf<Map<Char,Int>, Int>()
    val tempMap = getMapForWord(slidingWindowWord)
    mapOfMapsOfFreqs[tempMap] = 1 + (mapOfMapsOfFreqs[tempMap] ?: 0)
    for (c in t.subSequence(k, t.length)) {
        slidingWindowWord = slidingWindowWord.drop(1)
        slidingWindowWord += c
        val tempMap = getMapForWord(slidingWindowWord)
        mapOfMapsOfFreqs[tempMap] = 1 + (mapOfMapsOfFreqs[tempMap] ?: 0)
    }
    return mapOfMapsOfFreqs
}


val tests = arrayOf(
    Pair(Pair("esleastealaslatet", arrayOf("tesla")), arrayOf(3)),
    Pair(Pair("lrldrrrllddrrlllrddd", arrayOf("ldl", "rld")), arrayOf(1, 3)),
    Pair(Pair("kkkkkvvuvkvkkkvuuvkuukkuvvkukkvkkvuvukuk", arrayOf("vkuk", "uvku", "kukk")), arrayOf(5, 6, 1)),
    Pair(Pair("trhtrthtrthhhrtthrtrhhhtrrrhhrthrrrttrrttrthhrrrrtrtthhhhrrrtrtthrttthrthhthrhrh", arrayOf("rrrht", "tttrr", "rttrr", "rhrrr")), arrayOf(6, 5, 6, 1)),
    Pair(Pair("hjjijjhhhihhjjhjjhijjihjjihijiiihhihjjjihjjiijjijjhhjijjiijhjihiijjiiiijhihihhiihhiiihhiijhhhiijhijj", arrayOf("jihjhj", "hhjiii", "ihjhhh", "jjjiji")), arrayOf(10, 6, 2, 2))
)

fun check(test: Pair<Pair<String, Array<String>>, Array<Int>>): Boolean {
    val (args, staffSol) = test
    val studentSol = countAnagramSubstrings(args.first, args.second)
    return staffSol.contentEquals(studentSol)
}