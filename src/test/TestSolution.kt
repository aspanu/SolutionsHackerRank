import org.testng.annotations.Test

class TestSolution {

    @Test
    fun testCity() {
        val city = City(hasLibrary = false, roadsTo = HashMap<City, Boolean>())
        assert(city != null)
    }

}