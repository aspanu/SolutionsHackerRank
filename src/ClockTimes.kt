
import java.time.LocalTime.MIDNIGHT
import java.time.format.DateTimeFormatter

/**
 * Created by aspanu on 2017-09-12.
 */

fun main(args: Array<String>) {

    var currTime = MIDNIGHT
    var numTimes = 0

    do {
        if (maxNumSameDigits(currTime.format(DateTimeFormatter.ofPattern("h:mm"))) >= 3) {
            numTimes++
            println("Time: ${currTime.format(DateTimeFormatter.ofPattern("h:mm"))} has 3 or more digits.")
        }
        currTime = currTime.plusMinutes(1)
    } while (currTime != MIDNIGHT)

    println("Total number of times: $numTimes")

}

fun maxNumSameDigits(stringTime: String): Int {
    val charMap = mutableMapOf<Char, Int>()
    var maxNumDigits = -1


    for (c in stringTime.toCharArray()) {
        if (c.isDigit()) {
            if (!charMap.containsKey(c)) {
                charMap.put(c, 0)
            }
            charMap.put(c, (charMap[c]!! + 1))
            if (charMap[c]!! > maxNumDigits)
                maxNumDigits = charMap[c]!!
        }
    }
    return maxNumDigits
}
