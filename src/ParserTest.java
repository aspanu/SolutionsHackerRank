import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by aspanu on 2017-07-04.
 */
public class ParserTest {

    @Test
    public void testValidation() {
        GraphMaintainer gm = new GraphMaintainer();

        Parser.processLine(gm, null);
        Parser.processLine(gm, "");
        Parser.processLine(gm, "adsfasdfasd");
        Parser.processLine(gm, "091823als;klzcxklals;dfals;d09123u04aklsdfkl;adsfl;0912093kl;");
        Parser.processLine(gm, "ADD 123 123");
        Parser.processLine(gm, "REMOVE 1 2");
        Parser.processLine(gm, "IS LINKED 129312");
        Parser.processLine(gm, "ad\nd 1 2");
        Parser.processLine(gm, "^add 12312 1232$");
        Parser.processLine(gm, "random line, shouldn't cause program to fail");

        assertTrue(gm.getHistory().isEmpty());
        assertTrue(gm.getGraph().isEmpty());
    }

    @Test
    public void testGivenLines() {
        GraphMaintainer gm = new GraphMaintainer();

        Parser.processLine(gm, "add 1 2");
        Parser.processLine(gm, "random line, shouldn't cause program to fail");
        Parser.processLine(gm, "add 2 3");
        Parser.processLine(gm, "add 3 4");
        Parser.processLine(gm, "is linked 3 1");
        assertTrue(gm.isLinked(3L, 1L));

        Parser.processLine(gm, "remove 3 4");
        Parser.processLine(gm, "is linked 1 4");
        assertFalse(gm.isLinked(1L, 4L));
    }

    @Test
    public void testLargeNumbers() {
        GraphMaintainer gm = new GraphMaintainer();

        Parser.processLine(gm, "add 9223372036854775806 9123372036854775807");
        Parser.processLine(gm, "add 9223372036854775806 9123372036854775806");
        Parser.processLine(gm, "add 9123372036854775806 8123372036854775807");
        Parser.processLine(gm, "is linked 9223372036854775806 8123372036854775807");
        assertTrue(gm.isLinked(9223372036854775806L, 8123372036854775807L));

        Parser.processLine(gm, "remove 9123372036854775806 8123372036854775807");
        Parser.processLine(gm, "is linked 1 4");
        assertFalse(gm.isLinked(9223372036854775806L, 8123372036854775807L));
    }


}
