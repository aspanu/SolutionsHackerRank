import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by aspanu on 2017-07-03.
 */

public class AddInstructionTest {

    @Test
    public void testAddInstructionConstructor() {
        AddInstruction ai = new AddInstruction(1L, 3L);
        // TODO: TestNg is currently messing with me and isn't letting me use 'assertEquals' with 2 Longs
        // Giving up on fighting IntelliJ and using assertTrue instead, fix this at a later point please
        assertTrue(ai.getLeft() == 1L);
        assertTrue(ai.getRight() == 3L);
    }

    @Test
    public void testEqualAddInstruction() {
        AddInstruction aiEqual = new AddInstruction(4L, 4L);
        assertTrue(aiEqual.getLeft() == 4L);
        assertTrue(aiEqual.getRight() == 4L);
    }

}