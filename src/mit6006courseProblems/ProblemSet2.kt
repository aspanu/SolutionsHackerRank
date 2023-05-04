package mit6006courseProblems

// https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/mit6_006s20_ps2-questions/

// Problem 2-2
// a) Merge sort -> fastest time, can be done in place for extrinsic sorts
// b) "containing pointers to n comparable objects, pairs of which take Θ(log n) time to compare" ->
// why does a comparison between 2 objects in a list of 'n' objects depend on the total number of objects???
// anyway.... Merge sort -> still need to go through the list, can make a slight change to the algo
// to allow for non-decreasing sort
// c) Insertion sort -> in best case, this algo runs close to O(n) since most swaps will be single,
// and log(log(n)) is very small, so it is an 'almost sorted' array.

// Problem 2-3
// Do a binary search over k and not n. Start at k, if smaller than k, then binary search 0 -> k,
// if larger than k, then check n-k, if larger than n-k, do binary search n-k -> n, otherwise,
// do a binary search from k -> n-k (as you have more time!), and then you'll find Datum in ln(n - 2k)

// Problem 2-4
// Viewer: ID
// Viewer chat messages: array, with pointers to all of the nodes in different chats, if only 1 chat, all good
// Chat: doubly linked list
// When adding a new chat message, add it to each viewer's chat message array, and then have a pointer that points to that from the chat message that you add to the list
// When getting most recent chat messages, you just surf the linked list
// Sending is just O(1) since you add it to the requisite chat after adding it to the array of that viewer
// Banning means going through all of the array, going to all of the viewer chats and then pulling those messages out of the linked list, that is a O(1) op

// Problem 2-5
// a) Basically merge sort where you add the number of rooms necessary (i.e. the 'k').
//      For B1 = [(k11, s11, t11), arrayOf(k12, s12, t12), arrayOf(k13, s13, t13) ...]
//      and B2 = [(k21, s21, t21), arrayOf(k22, s22, t22), arrayOf(k23, s23, t23) ...]
//      Start a new booking, that lasts from min(s11, s21) to min(t11 or t12, s21 or s11), and then that k
//      If s11 == s21, just add k11 and k21, and then keep going, adding up the bookings one at a time
//      There is some complexity keeping track of the pointers on each list and knowing when the next 'stop' point is
//
// b) Go through every R and convert it into a B, then go through the Bs and merge them together after sorting?
//      Probably need to sort first, then when sorted, go through the list and for each adjacent R,
//      Combine together, where if they are adjacent they just glob into each other, if they are disjoint, then close the current B
//      If they are part of this B, then add together and decide whether to split out this B or not
//
// c)


/*
    Tests:
    tests = (
    (
        ((2, 19), arrayOf(17, 18), arrayOf(12, 25), arrayOf(5, 15), arrayOf(9, 11)),
        ((1, 2, 5), arrayOf(2, 5, 9), arrayOf(3, 9, 11), arrayOf(2, 11, 12), arrayOf(3, 12, 15), arrayOf(2, 15, 17), arrayOf(3, 17, 18), arrayOf(2, 18, 19), arrayOf(1, 19, 25)),
    ),
    (
        ((7, 20), arrayOf(35, 44), arrayOf(71, 80), arrayOf(72, 89), arrayOf(2, 77), arrayOf(7, 78), arrayOf(23, 26), arrayOf(36, 44), arrayOf(44, 86), arrayOf(27, 100)),
        ((1, 2, 7), arrayOf(3, 7, 20), arrayOf(2, 20, 23), arrayOf(3, 23, 26), arrayOf(2, 26, 27), arrayOf(3, 27, 35), arrayOf(4, 35, 36), arrayOf(5, 36, 44), arrayOf(4, 44, 71), arrayOf(5, 71, 72), arrayOf(6, 72, 77), arrayOf(5, 77, 78), arrayOf(4, 78, 80), arrayOf(3, 80, 86), arrayOf(2, 86, 89), arrayOf(1, 89, 100)),
    ),
    (
        ((32, 89), arrayOf(112, 390), arrayOf(163, 247), arrayOf(50, 75), arrayOf(107, 385), arrayOf(62, 276), arrayOf(82, 312), arrayOf(18, 104), arrayOf(136, 351), arrayOf(72, 170), arrayOf(151, 356), arrayOf(104, 175), arrayOf(65, 161), arrayOf(215, 345), arrayOf(60, 179), arrayOf(182, 269), arrayOf(101, 212), arrayOf(159, 278), arrayOf(73, 144), arrayOf(216, 242)),
        ((1, 18, 32), arrayOf(2, 32, 50), arrayOf(3, 50, 60), arrayOf(4, 60, 62), arrayOf(5, 62, 65), arrayOf(6, 65, 72), arrayOf(7, 72, 73), arrayOf(8, 73, 75), arrayOf(7, 75, 82), arrayOf(8, 82, 89), arrayOf(7, 89, 101), arrayOf(8, 101, 107), arrayOf(9, 107, 112), arrayOf(10, 112, 136), arrayOf(11, 136, 144), arrayOf(10, 144, 151), arrayOf(11, 151, 159), arrayOf(12, 159, 161), arrayOf(11, 161, 163), arrayOf(12, 163, 170), arrayOf(11, 170, 175), arrayOf(10, 175, 179), arrayOf(9, 179, 182), arrayOf(10, 182, 212), arrayOf(9, 212, 215), arrayOf(10, 215, 216), arrayOf(11, 216, 242), arrayOf(10, 242, 247), arrayOf(9, 247, 269), arrayOf(8, 269, 276), arrayOf(7, 276, 278), arrayOf(6, 278, 312), arrayOf(5, 312, 345), arrayOf(4, 345, 351), arrayOf(3, 351, 356), arrayOf(2, 356, 385), arrayOf(1, 385, 390)),
    ),
    (
        ((273, 395), arrayOf(127, 623), arrayOf(102, 618), arrayOf(141, 191), arrayOf(458, 899), arrayOf(379, 398), arrayOf(16, 815), arrayOf(738, 843), arrayOf(297, 572), arrayOf(87, 343), arrayOf(206, 788), arrayOf(213, 651), arrayOf(679, 797), arrayOf(222, 255), arrayOf(62, 829), arrayOf(109, 166), arrayOf(366, 617), arrayOf(128, 476), arrayOf(122, 425), arrayOf(339, 703), arrayOf(107, 347), arrayOf(213, 419), arrayOf(259, 407), arrayOf(150, 249), arrayOf(127, 594), arrayOf(587, 813), arrayOf(85, 759), arrayOf(242, 303), arrayOf(174, 579), arrayOf(125, 158)),
        ((1, 16, 62), arrayOf(2, 62, 85), arrayOf(3, 85, 87), arrayOf(4, 87, 102), arrayOf(5, 102, 107), arrayOf(6, 107, 109), arrayOf(7, 109, 122), arrayOf(8, 122, 125), arrayOf(9, 125, 127), arrayOf(11, 127, 128), arrayOf(12, 128, 141), arrayOf(13, 141, 150), arrayOf(14, 150, 158), arrayOf(13, 158, 166), arrayOf(12, 166, 174), arrayOf(13, 174, 191), arrayOf(12, 191, 206), arrayOf(13, 206, 213), arrayOf(15, 213, 222), arrayOf(16, 222, 242), arrayOf(17, 242, 249), arrayOf(16, 249, 255), arrayOf(15, 255, 259), arrayOf(16, 259, 273), arrayOf(17, 273, 297), arrayOf(18, 297, 303), arrayOf(17, 303, 339), arrayOf(18, 339, 343), arrayOf(17, 343, 347), arrayOf(16, 347, 366), arrayOf(17, 366, 379), arrayOf(18, 379, 395), arrayOf(17, 395, 398), arrayOf(16, 398, 407), arrayOf(15, 407, 419), arrayOf(14, 419, 425), arrayOf(13, 425, 458), arrayOf(14, 458, 476), arrayOf(13, 476, 572), arrayOf(12, 572, 579), arrayOf(11, 579, 587), arrayOf(12, 587, 594), arrayOf(11, 594, 617), arrayOf(10, 617, 618), arrayOf(9, 618, 623), arrayOf(8, 623, 651), arrayOf(7, 651, 679), arrayOf(8, 679, 703), arrayOf(7, 703, 738), arrayOf(8, 738, 759), arrayOf(7, 759, 788), arrayOf(6, 788, 797), arrayOf(5, 797, 813), arrayOf(4, 813, 815), arrayOf(3, 815, 829), arrayOf(2, 829, 843), arrayOf(1, 843, 899)),
    ),
    (
        ((214, 1957), arrayOf(839, 1997), arrayOf(465, 2371), arrayOf(154, 1369), arrayOf(950, 1804), arrayOf(565, 1715), arrayOf(1128, 1816), arrayOf(58, 1421), arrayOf(620, 1623), arrayOf(948, 1606), arrayOf(1034, 2105), arrayOf(323, 2320), arrayOf(1874, 2171), arrayOf(335, 537), arrayOf(969, 2083), arrayOf(1072, 2355), arrayOf(285, 1975), arrayOf(127, 137), arrayOf(257, 1122), arrayOf(479, 2317), arrayOf(1192, 1325), arrayOf(106, 1858), arrayOf(442, 649), arrayOf(339, 2483), arrayOf(587, 2469), arrayOf(566, 1022), arrayOf(1246, 1753), arrayOf(708, 2197), arrayOf(367, 1190), arrayOf(110, 1899), arrayOf(545, 1745), arrayOf(1280, 1375), arrayOf(1313, 2323), arrayOf(703, 1248), arrayOf(142, 1869), arrayOf(265, 1247), arrayOf(670, 1395), arrayOf(362, 1942), arrayOf(321, 2455), arrayOf(548, 897), arrayOf(553, 605), arrayOf(880, 2305), arrayOf(1519, 1800), arrayOf(1536, 1852), arrayOf(797, 2450), arrayOf(496, 2491), arrayOf(348, 1444), arrayOf(1083, 1315), arrayOf(396, 1071), arrayOf(143, 1757)),
        ((1, 58, 106), arrayOf(2, 106, 110), arrayOf(3, 110, 127), arrayOf(4, 127, 137), arrayOf(3, 137, 142), arrayOf(4, 142, 143), arrayOf(5, 143, 154), arrayOf(6, 154, 214), arrayOf(7, 214, 257), arrayOf(8, 257, 265), arrayOf(9, 265, 285), arrayOf(10, 285, 321), arrayOf(11, 321, 323), arrayOf(12, 323, 335), arrayOf(13, 335, 339), arrayOf(14, 339, 348), arrayOf(15, 348, 362), arrayOf(16, 362, 367), arrayOf(17, 367, 396), arrayOf(18, 396, 442), arrayOf(19, 442, 465), arrayOf(20, 465, 479), arrayOf(21, 479, 496), arrayOf(22, 496, 537), arrayOf(21, 537, 545), arrayOf(22, 545, 548), arrayOf(23, 548, 553), arrayOf(24, 553, 565), arrayOf(25, 565, 566), arrayOf(26, 566, 587), arrayOf(27, 587, 605), arrayOf(26, 605, 620), arrayOf(27, 620, 649), arrayOf(26, 649, 670), arrayOf(27, 670, 703), arrayOf(28, 703, 708), arrayOf(29, 708, 797), arrayOf(30, 797, 839), arrayOf(31, 839, 880), arrayOf(32, 880, 897), arrayOf(31, 897, 948), arrayOf(32, 948, 950), arrayOf(33, 950, 969), arrayOf(34, 969, 1022), arrayOf(33, 1022, 1034), arrayOf(34, 1034, 1071), arrayOf(33, 1071, 1072), arrayOf(34, 1072, 1083), arrayOf(35, 1083, 1122), arrayOf(34, 1122, 1128), arrayOf(35, 1128, 1190), arrayOf(34, 1190, 1192), arrayOf(35, 1192, 1246), arrayOf(36, 1246, 1247), arrayOf(35, 1247, 1248), arrayOf(34, 1248, 1280), arrayOf(35, 1280, 1313), arrayOf(36, 1313, 1315), arrayOf(35, 1315, 1325), arrayOf(34, 1325, 1369), arrayOf(33, 1369, 1375), arrayOf(32, 1375, 1395), arrayOf(31, 1395, 1421), arrayOf(30, 1421, 1444), arrayOf(29, 1444, 1519), arrayOf(30, 1519, 1536), arrayOf(31, 1536, 1606), arrayOf(30, 1606, 1623), arrayOf(29, 1623, 1715), arrayOf(28, 1715, 1745), arrayOf(27, 1745, 1753), arrayOf(26, 1753, 1757), arrayOf(25, 1757, 1800), arrayOf(24, 1800, 1804), arrayOf(23, 1804, 1816), arrayOf(22, 1816, 1852), arrayOf(21, 1852, 1858), arrayOf(20, 1858, 1869), arrayOf(19, 1869, 1874), arrayOf(20, 1874, 1899), arrayOf(19, 1899, 1942), arrayOf(18, 1942, 1957), arrayOf(17, 1957, 1975), arrayOf(16, 1975, 1997), arrayOf(15, 1997, 2083), arrayOf(14, 2083, 2105), arrayOf(13, 2105, 2171), arrayOf(12, 2171, 2197), arrayOf(11, 2197, 2305), arrayOf(10, 2305, 2317), arrayOf(9, 2317, 2320), arrayOf(8, 2320, 2323), arrayOf(7, 2323, 2355), arrayOf(6, 2355, 2371), arrayOf(5, 2371, 2450), arrayOf(4, 2450, 2455), arrayOf(3, 2455, 2469), arrayOf(2, 2469, 2483), arrayOf(1, 2483, 2491)),
    ),
)
 */

fun rFromArray(input: Array<Int>): R {
    return R(input[0], input[1])
}

fun bFromArray(input: Array<Int>): B {
    return B(input[0], input[1], input[2])
}

fun testAll() {
    val Rs = mutableListOf<List<R>>()
    val Bs = mutableListOf<List<B>>()
    Rs.add(listOf(arrayOf(2, 19), arrayOf(17, 18), arrayOf(12, 25), arrayOf(5, 15), arrayOf(9, 11)).map { rFromArray(it) })
    Bs.add(listOf(arrayOf(1, 2, 5), arrayOf(2, 5, 9), arrayOf(3, 9, 11), arrayOf(2, 11, 12), arrayOf(3, 12, 15), arrayOf(2, 15, 17), arrayOf(3, 17, 18), arrayOf(2, 18, 19), arrayOf(1, 19, 25)).map { bFromArray(it) })
    Rs.add(listOf(arrayOf(7, 20), arrayOf(35, 44), arrayOf(71, 80), arrayOf(72, 89), arrayOf(2, 77), arrayOf(7, 78), arrayOf(23, 26), arrayOf(36, 44), arrayOf(44, 86), arrayOf(27, 100)).map { rFromArray(it) })
    Bs.add(listOf(arrayOf(1, 2, 7), arrayOf(3, 7, 20), arrayOf(2, 20, 23), arrayOf(3, 23, 26), arrayOf(2, 26, 27), arrayOf(3, 27, 35), arrayOf(4, 35, 36), arrayOf(5, 36, 44), arrayOf(4, 44, 71), arrayOf(5, 71, 72), arrayOf(6, 72, 77), arrayOf(5, 77, 78), arrayOf(4, 78, 80), arrayOf(3, 80, 86), arrayOf(2, 86, 89), arrayOf(1, 89, 100)).map { bFromArray(it) })
    Rs.add(listOf(arrayOf(32, 89), arrayOf(112, 390), arrayOf(163, 247), arrayOf(50, 75), arrayOf(107, 385), arrayOf(62, 276), arrayOf(82, 312), arrayOf(18, 104), arrayOf(136, 351), arrayOf(72, 170), arrayOf(151, 356), arrayOf(104, 175), arrayOf(65, 161), arrayOf(215, 345), arrayOf(60, 179), arrayOf(182, 269), arrayOf(101, 212), arrayOf(159, 278), arrayOf(73, 144), arrayOf(216, 242)).map { rFromArray(it) })
    Bs.add(listOf(arrayOf(1, 18, 32), arrayOf(2, 32, 50), arrayOf(3, 50, 60), arrayOf(4, 60, 62), arrayOf(5, 62, 65), arrayOf(6, 65, 72), arrayOf(7, 72, 73), arrayOf(8, 73, 75), arrayOf(7, 75, 82), arrayOf(8, 82, 89), arrayOf(7, 89, 101), arrayOf(8, 101, 107), arrayOf(9, 107, 112), arrayOf(10, 112, 136), arrayOf(11, 136, 144), arrayOf(10, 144, 151), arrayOf(11, 151, 159), arrayOf(12, 159, 161), arrayOf(11, 161, 163), arrayOf(12, 163, 170), arrayOf(11, 170, 175), arrayOf(10, 175, 179), arrayOf(9, 179, 182), arrayOf(10, 182, 212), arrayOf(9, 212, 215), arrayOf(10, 215, 216), arrayOf(11, 216, 242), arrayOf(10, 242, 247), arrayOf(9, 247, 269), arrayOf(8, 269, 276), arrayOf(7, 276, 278), arrayOf(6, 278, 312), arrayOf(5, 312, 345), arrayOf(4, 345, 351), arrayOf(3, 351, 356), arrayOf(2, 356, 385), arrayOf(1, 385, 390)).map { bFromArray(it) })
    Rs.add(listOf(arrayOf(273, 395), arrayOf(127, 623), arrayOf(102, 618), arrayOf(141, 191), arrayOf(458, 899), arrayOf(379, 398), arrayOf(16, 815), arrayOf(738, 843), arrayOf(297, 572), arrayOf(87, 343), arrayOf(206, 788), arrayOf(213, 651), arrayOf(679, 797), arrayOf(222, 255), arrayOf(62, 829), arrayOf(109, 166), arrayOf(366, 617), arrayOf(128, 476), arrayOf(122, 425), arrayOf(339, 703), arrayOf(107, 347), arrayOf(213, 419), arrayOf(259, 407), arrayOf(150, 249), arrayOf(127, 594), arrayOf(587, 813), arrayOf(85, 759), arrayOf(242, 303), arrayOf(174, 579), arrayOf(125, 158)).map { rFromArray(it) })
    Bs.add(listOf(arrayOf(1, 16, 62), arrayOf(2, 62, 85), arrayOf(3, 85, 87), arrayOf(4, 87, 102), arrayOf(5, 102, 107), arrayOf(6, 107, 109), arrayOf(7, 109, 122), arrayOf(8, 122, 125), arrayOf(9, 125, 127), arrayOf(11, 127, 128), arrayOf(12, 128, 141), arrayOf(13, 141, 150), arrayOf(14, 150, 158), arrayOf(13, 158, 166), arrayOf(12, 166, 174), arrayOf(13, 174, 191), arrayOf(12, 191, 206), arrayOf(13, 206, 213), arrayOf(15, 213, 222), arrayOf(16, 222, 242), arrayOf(17, 242, 249), arrayOf(16, 249, 255), arrayOf(15, 255, 259), arrayOf(16, 259, 273), arrayOf(17, 273, 297), arrayOf(18, 297, 303), arrayOf(17, 303, 339), arrayOf(18, 339, 343), arrayOf(17, 343, 347), arrayOf(16, 347, 366), arrayOf(17, 366, 379), arrayOf(18, 379, 395), arrayOf(17, 395, 398), arrayOf(16, 398, 407), arrayOf(15, 407, 419), arrayOf(14, 419, 425), arrayOf(13, 425, 458), arrayOf(14, 458, 476), arrayOf(13, 476, 572), arrayOf(12, 572, 579), arrayOf(11, 579, 587), arrayOf(12, 587, 594), arrayOf(11, 594, 617), arrayOf(10, 617, 618), arrayOf(9, 618, 623), arrayOf(8, 623, 651), arrayOf(7, 651, 679), arrayOf(8, 679, 703), arrayOf(7, 703, 738), arrayOf(8, 738, 759), arrayOf(7, 759, 788), arrayOf(6, 788, 797), arrayOf(5, 797, 813), arrayOf(4, 813, 815), arrayOf(3, 815, 829), arrayOf(2, 829, 843), arrayOf(1, 843, 899)).map { bFromArray(it) })
    Rs.add(listOf(arrayOf(214, 1957), arrayOf(839, 1997), arrayOf(465, 2371), arrayOf(154, 1369), arrayOf(950, 1804), arrayOf(565, 1715), arrayOf(1128, 1816), arrayOf(58, 1421), arrayOf(620, 1623), arrayOf(948, 1606), arrayOf(1034, 2105), arrayOf(323, 2320), arrayOf(1874, 2171), arrayOf(335, 537), arrayOf(969, 2083), arrayOf(1072, 2355), arrayOf(285, 1975), arrayOf(127, 137), arrayOf(257, 1122), arrayOf(479, 2317), arrayOf(1192, 1325), arrayOf(106, 1858), arrayOf(442, 649), arrayOf(339, 2483), arrayOf(587, 2469), arrayOf(566, 1022), arrayOf(1246, 1753), arrayOf(708, 2197), arrayOf(367, 1190), arrayOf(110, 1899), arrayOf(545, 1745), arrayOf(1280, 1375), arrayOf(1313, 2323), arrayOf(703, 1248), arrayOf(142, 1869), arrayOf(265, 1247), arrayOf(670, 1395), arrayOf(362, 1942), arrayOf(321, 2455), arrayOf(548, 897), arrayOf(553, 605), arrayOf(880, 2305), arrayOf(1519, 1800), arrayOf(1536, 1852), arrayOf(797, 2450), arrayOf(496, 2491), arrayOf(348, 1444), arrayOf(1083, 1315), arrayOf(396, 1071), arrayOf(143, 1757)).map { rFromArray(it) })
    Bs.add(listOf(arrayOf(1, 58, 106), arrayOf(2, 106, 110), arrayOf(3, 110, 127), arrayOf(4, 127, 137), arrayOf(3, 137, 142), arrayOf(4, 142, 143), arrayOf(5, 143, 154), arrayOf(6, 154, 214), arrayOf(7, 214, 257), arrayOf(8, 257, 265), arrayOf(9, 265, 285), arrayOf(10, 285, 321), arrayOf(11, 321, 323), arrayOf(12, 323, 335), arrayOf(13, 335, 339), arrayOf(14, 339, 348), arrayOf(15, 348, 362), arrayOf(16, 362, 367), arrayOf(17, 367, 396), arrayOf(18, 396, 442), arrayOf(19, 442, 465), arrayOf(20, 465, 479), arrayOf(21, 479, 496), arrayOf(22, 496, 537), arrayOf(21, 537, 545), arrayOf(22, 545, 548), arrayOf(23, 548, 553), arrayOf(24, 553, 565), arrayOf(25, 565, 566), arrayOf(26, 566, 587), arrayOf(27, 587, 605), arrayOf(26, 605, 620), arrayOf(27, 620, 649), arrayOf(26, 649, 670), arrayOf(27, 670, 703), arrayOf(28, 703, 708), arrayOf(29, 708, 797), arrayOf(30, 797, 839), arrayOf(31, 839, 880), arrayOf(32, 880, 897), arrayOf(31, 897, 948), arrayOf(32, 948, 950), arrayOf(33, 950, 969), arrayOf(34, 969, 1022), arrayOf(33, 1022, 1034), arrayOf(34, 1034, 1071), arrayOf(33, 1071, 1072), arrayOf(34, 1072, 1083), arrayOf(35, 1083, 1122), arrayOf(34, 1122, 1128), arrayOf(35, 1128, 1190), arrayOf(34, 1190, 1192), arrayOf(35, 1192, 1246), arrayOf(36, 1246, 1247), arrayOf(35, 1247, 1248), arrayOf(34, 1248, 1280), arrayOf(35, 1280, 1313), arrayOf(36, 1313, 1315), arrayOf(35, 1315, 1325), arrayOf(34, 1325, 1369), arrayOf(33, 1369, 1375), arrayOf(32, 1375, 1395), arrayOf(31, 1395, 1421), arrayOf(30, 1421, 1444), arrayOf(29, 1444, 1519), arrayOf(30, 1519, 1536), arrayOf(31, 1536, 1606), arrayOf(30, 1606, 1623), arrayOf(29, 1623, 1715), arrayOf(28, 1715, 1745), arrayOf(27, 1745, 1753), arrayOf(26, 1753, 1757), arrayOf(25, 1757, 1800), arrayOf(24, 1800, 1804), arrayOf(23, 1804, 1816), arrayOf(22, 1816, 1852), arrayOf(21, 1852, 1858), arrayOf(20, 1858, 1869), arrayOf(19, 1869, 1874), arrayOf(20, 1874, 1899), arrayOf(19, 1899, 1942), arrayOf(18, 1942, 1957), arrayOf(17, 1957, 1975), arrayOf(16, 1975, 1997), arrayOf(15, 1997, 2083), arrayOf(14, 2083, 2105), arrayOf(13, 2105, 2171), arrayOf(12, 2171, 2197), arrayOf(11, 2197, 2305), arrayOf(10, 2305, 2317), arrayOf(9, 2317, 2320), arrayOf(8, 2320, 2323), arrayOf(7, 2323, 2355), arrayOf(6, 2355, 2371), arrayOf(5, 2371, 2450), arrayOf(4, 2450, 2455), arrayOf(3, 2455, 2469), arrayOf(2, 2469, 2483), arrayOf(1, 2483, 2491)).map { bFromArray(it) })
    for (i in Rs.indices) {
        assert(compareBs(Bs[i], satisfyingBooking(Rs[i])))
    }
}

fun compareBs(expected: List<B>, actual: List<B>): Boolean {
    if (expected.size != actual.size) { println("Not the same size!"); return false }

    for (i in expected.indices) {
        if (expected[i] != actual[i]) { println("Not the same item for index $i !"); return false }
    }

    return true
}

data class R (val /* s */ start: Int, val /* t */ end: Int)
data class B (val /* k */ numRooms: Int, val /* s */ start: Int, val /* t */ end: Int)

fun satisfyingBooking(requests: List<R>): List<B> {
//    requests.sortedBy {  }
    return listOf()
}

