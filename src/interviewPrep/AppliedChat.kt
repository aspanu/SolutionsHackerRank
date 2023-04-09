package interviewPrep

fun main() {
    println(encodeList(listOf("babble", "bobble", "boggle", "bobgle")))
}

const val SENTINEL = ""
fun conflictEncode(map: MutableMap<String, String>, encoded: String, word: String) {
        val conflictedEncoding = createConflictEncoding(encoded, word)

        if (isInsideMap(map, conflictedEncoding)) {
            if (isSentinel(map, conflictedEncoding)) {
                conflictEncode(map, conflictedEncoding, word)
            } else {
                conflictEncode(map, conflictedEncoding, word)
                conflictEncode(map, conflictedEncoding, map[conflictedEncoding]!!)
            }
        } else {
            addToMap(map, conflictedEncoding, word)
        }

}

fun createConflictEncoding(encoded: String, word: String): String {
    // Take substring before number, add a character to it, take away 1 from number, and add last character

}

fun isSentinel(map: Map<String, String>, conflictedEncoding: String): Boolean {
    return SENTINEL == map[conflictedEncoding]
}

fun encodeList(stringList: List<String>): Map<String, String> {
    val map = mutableMapOf<String, String>()
    for (word in stringList) {
        val encoded = createEncoding(word)
        if (isInsideMap(map, encoded)) {
            conflictEncode(map, encoded, word)
        } else {
            addToMap(map, encoded, word)
        }
    }
    return map.filter { it.key != SENTINEL }
}

fun addToMap(map: MutableMap<String, String>, encoded: String, word: String) {
    map[encoded] = word
}

fun isInsideMap(map: Map<String, String>, encoded: String): Boolean {
    return map.containsKey(encoded)
}

fun createEncoding(word: String): String {
    return word[0] + (word.length - 2).toString() + word[word.length - 1]
}
