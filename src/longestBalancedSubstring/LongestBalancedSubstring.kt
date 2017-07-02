package longestBalancedSubstring

/**
 * Created by aspanu on 2017-06-20
 * Solution for this problem:
 * Given a string of only '(' and ')', find the length of the longest 'balanced' substring
 * Where balanced means that it was an equal number of '(' and ')' - so for each open parenthesis, you have a closing parenthesis
 * The '(' needs to come before the ')'
 */

fun main(args: Array<String>) {

//    println(longestBalancedSubstring.pp(6, longestBalancedSubstring.internetSolution("((()))")))
//    println(longestBalancedSubstring.pp(4, longestBalancedSubstring.internetSolution("(())")))
//    println(longestBalancedSubstring.pp(2, longestBalancedSubstring.internetSolution(")))())))")))
//    println(longestBalancedSubstring.pp(6, longestBalancedSubstring.internetSolution("()()()")))
    println(pp(8, internetSolution("((())))))))()()()()")))

    println(pp(8, muaazSolution("((())))))))()()()()")))


}

fun muaazSolution(parenLine: String): Int {
    var longestLength = 0
    for (i in 0..parenLine.length) {
    }
    return 0
}

fun pp(realAnswer: Int, givenAnswer: Int): String {
    return "Should be: $realAnswer, and it is: $givenAnswer"
}

fun internetSolution(parenLine: String) : Int {
    var parens = 0
    var lastOpenIndex = 0
    var lastClosedIndex = -1

    while (lastOpenIndex < parenLine.length && lastClosedIndex < parenLine.length) {
        if (parenLine[lastOpenIndex] == ')') lastOpenIndex++
        else if (lastClosedIndex < lastOpenIndex) lastClosedIndex++
        else if (parenLine[lastClosedIndex] == '(') lastClosedIndex++
        else {
            parens++
            lastOpenIndex++
        }
    }

    return parens * 2
}

fun muaazRecursion(str: String, startIndex: Int): Int {
    var l = 0
    var i = startIndex + 1


    while (i < str.length) {
        if (str[i] == '(') {
            // Find the closing bracking for this substring
            l += muaazRecursion(str, i)
            if (l > 0) {
                i += 1
            } else {
                // If the the subcall couldn't find a match, we won't either
                return -1
            }
        }

        if (str[i] == ')') {
            // We found our closing paran
            l = i - startIndex
            break
        }

    }

    // If we never found our closing bracket and nor did a subcall
    if (l == 0) {
        return -1
    } else {
        // If we found our closing bracket or a subcall did
        return l
    }
}


// Internet solution:

/*
parens = 0
last_open = 0
last_closed = 0
while last_open < len(str) && last_closed < len(str):
    if str[last_open] == ')':
        # We are looking for the next open paren.
       last_open += 1
    elif last_closed < last_open:
       # Start our search for a last closed after the current char
       last_closed = last_open + 1
    elif str[last_closed] == '(':
       # still looking for a close pair
       last_closed += 1
    else:
       # We found a matching pair.
       parens += 1
       last_open += 1
# and now parens has the correct answer.

*/


// The below functions are a bad descent into hell. Let's use the internet instead
fun joinBalancedLocationsIfPossibleAndReturnLargestLength(balancedLocations: List<BalancedLocation>) : Int {
    val sortedLocations = balancedLocations.sortedBy { it.end }
    var prevBalancedLocation = BalancedLocation(start = -1, end = -1)
    var longestRun = 0
    for (balancedLocation in sortedLocations) {
        if (balancedLocation.start == prevBalancedLocation.end + 1) prevBalancedLocation.combineLocation(balancedLocation)
        else prevBalancedLocation = balancedLocation

        if (prevBalancedLocation.size() > longestRun) longestRun = prevBalancedLocation.size()
    }
    return longestRun
}

fun findBalancedSubStrings(parenLine : String) : List<BalancedLocation> {
    val balancedLocations = mutableListOf<BalancedLocation>()

    val orderArray = getOrderArrayFromString(parenLine)

    return balancedLocations
}

fun getOrderArrayFromString(parenLine: String) : List<Int> {
    val orderArray = mutableListOf<Int>()
    var c = 0
    for (char in parenLine) {
        if (char == '(') c++
        else if (c < 0) c = -1
        else c--
        orderArray.add(c)
    }

    return orderArray
}

/**
 * Defines a location which has a perfectly balanced substring
 * A perfectly balanced substring has starts and ends with an equal number of parentheses
 */
data class BalancedLocation(val start: Int, var end: Int) {
    fun combineLocation(newBalancedLocation: BalancedLocation) {
        if ((end + 1) != newBalancedLocation.start) throw UnsupportedOperationException("wtf are you doing?")
        this.end = newBalancedLocation.end
    }
    fun size(): Int {
        return end - start
    }
}