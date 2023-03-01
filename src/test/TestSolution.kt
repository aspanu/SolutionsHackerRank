
class TestSolution {

    fun testCity() {
        val city = City(hasLibrary = false, roadsTo = HashMap<City, Boolean>())
        assert(city != null)
    }

}