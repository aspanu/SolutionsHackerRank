package knightFinder

// distinct numbers to dial with a specific length
// knight always starts at 1
//

// 1 2 3
// 4 5 6
// 7 8 9
//   0

fun main(args: Array<String>) {
//    findNumOfPhoneNumbersWithAKnight(1)
//    findNumOfPhoneNumbersWithAKnight(2)
    val finder = FindNumbers()
    finder.findNumOfPhoneNumbersWithAKnight(7)
}

data class Key(val depth : Int, val position : Int)

class FindNumbers {

    val cache = HashMap<Key, Int>()

    fun findNumOfPhoneNumbersWithAKnight(len : Int) : Int {
        val currentPosition = 1
        val currentDepth = 1
        println("Number of phone numbers: " + findRecursive(len, currentDepth, currentPosition))

        return 0
    }

    fun findRecursive(len: Int, currentDepth: Int, currentPosition: Int): Int {
        val key = Key(currentDepth, currentPosition)
        if (cache.containsKey(key)) return cache.get(key)!!

        if (len == currentDepth) {
            return 1
        }
        var numberOfPhoneNumbers = 0
        val newDepth = currentDepth + 1
        for (point in allPossibleCorrectJumps(currentPosition)) {
            numberOfPhoneNumbers += findRecursive(len, newDepth, point)
        }

        cache.put(key, numberOfPhoneNumbers)

        return numberOfPhoneNumbers
    }


    fun  allPossibleCorrectJumps(currentPosition: Int): Set<Int> {
        if (currentPosition == 1) return setOf(6, 8)
        if (currentPosition == 2) return setOf(7, 9)
        if (currentPosition == 3) return setOf(4, 8)
        if (currentPosition == 4) return setOf(3, 9, 0)
        if (currentPosition == 6) return setOf(1, 7, 0)
        if (currentPosition == 7) return setOf(2, 6)
        if (currentPosition == 8) return setOf(1, 3)
        if (currentPosition == 9) return setOf(2, 4)
        else return setOf(4, 6)
    }

}

