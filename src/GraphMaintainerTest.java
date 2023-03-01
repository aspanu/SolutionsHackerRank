//import org.testng.annotations.Test;
//
//import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
///**
// * Created by aspanu on 2017-07-03.
// */
//public class GraphMaintainerTest {
//
//
//    @Test
//    public void testAddFunction() {
//        AddInstruction ai = new AddInstruction(1L, 2L);
//        GraphMaintainer gm = new GraphMaintainer();
//        gm.add(ai);
//
//        assertTrue(gm.getHistory().size() == 1);
//        assertTrue(gm.getHistory().contains(ai));
//        assertTrue(gm.getGraph().keySet().size() == 2);
//        assertTrue(gm.getGraph().containsKey(1L));
//        assertTrue(gm.getGraph().containsKey(2L));
//        assertTrue(gm.getGraph().get(1L).size() == 2);
//        assertTrue(gm.getGraph().get(2L).size() == 2);
//        assertTrue(gm.getGraph().get(1L).contains(1L));
//        assertTrue(gm.getGraph().get(1L).contains(2L));
//        assertTrue(gm.getGraph().get(2L).contains(1L));
//        assertTrue(gm.getGraph().get(2L).contains(2L));
//
//        ai = new AddInstruction(2L, 3L);
//        gm.add(ai);
//
//        assertTrue(gm.getHistory().size() == 2);
//        assertTrue(gm.getHistory().contains(ai));
//        assertTrue(gm.getGraph().keySet().size() == 3);
//        assertTrue(gm.getGraph().containsKey(1L));
//        assertTrue(gm.getGraph().containsKey(2L));
//        assertTrue(gm.getGraph().containsKey(3L));
//        assertTrue(gm.getGraph().get(1L).size() == 3);
//        assertTrue(gm.getGraph().get(2L).size() == 3);
//        assertTrue(gm.getGraph().get(3L).size() == 3);
//        assertTrue(gm.getGraph().get(1L).contains(1L));
//        assertTrue(gm.getGraph().get(1L).contains(2L));
//        assertTrue(gm.getGraph().get(1L).contains(3L));
//        assertTrue(gm.getGraph().get(2L).contains(1L));
//        assertTrue(gm.getGraph().get(2L).contains(2L));
//        assertTrue(gm.getGraph().get(2L).contains(3L));
//        assertTrue(gm.getGraph().get(3L).contains(1L));
//        assertTrue(gm.getGraph().get(3L).contains(2L));
//        assertTrue(gm.getGraph().get(3L).contains(3L));
//    }
//
//    @Test
//    public void testIfNodeIsInTheGraphItIsLinkedToItself() {
//        GraphMaintainer gm = new GraphMaintainer();
//        gm.add(new AddInstruction(1L, 2L));
//
//        assertTrue(gm.isLinked(1L, 1L));
//        assertTrue(gm.isLinked(1L, 2L));
//        assertTrue(gm.isLinked(2L, 2L));
//    }
//
//    @Test
//    public void testLinking() {
//        GraphMaintainer gm = new GraphMaintainer();
//        gm.add(new AddInstruction(1L, 2L));
//        gm.add(new AddInstruction(2L, 3L));
//        gm.add(new AddInstruction(3L, 4L));
//
//        assertTrue(gm.isLinked(3L, 1L));
//    }
//
//    @Test
//    public void testRemoval() {
//        GraphMaintainer gm = new GraphMaintainer();
//        gm.add(new AddInstruction(1L, 2L));
//        gm.add(new AddInstruction(2L, 3L));
//        gm.add(new AddInstruction(3L, 4L));
//        gm.remove(new AddInstruction(3L, 4L));
//
//        assertFalse(gm.isLinked(1L, 4L));
//    }
//
//    @Test
//    public void testMultipleSubGraphs() {
//        GraphMaintainer gm = new GraphMaintainer();
//        gm.add(new AddInstruction(1L, 2L));
//        gm.add(new AddInstruction(2L, 3L));
//        gm.add(new AddInstruction(3L, 4L));
//        gm.add(new AddInstruction(10L, 11L));
//        gm.add(new AddInstruction(11L, 12L));
//
//        assertTrue(gm.isLinked(1L, 1L));
//        assertTrue(gm.isLinked(1L, 2L));
//        assertTrue(gm.isLinked(1L, 3L));
//        assertTrue(gm.isLinked(1L, 4L));
//        assertFalse(gm.isLinked(1L, 10L));
//        assertFalse(gm.isLinked(1L, 11L));
//        assertFalse(gm.isLinked(1L, 12L));
//
//        assertTrue(gm.isLinked(2L, 1L));
//        assertTrue(gm.isLinked(2L, 2L));
//        assertTrue(gm.isLinked(2L, 3L));
//        assertTrue(gm.isLinked(2L, 4L));
//        assertFalse(gm.isLinked(2L, 10L));
//        assertFalse(gm.isLinked(2L, 11L));
//        assertFalse(gm.isLinked(2L, 12L));
//
//        assertTrue(gm.isLinked(3L, 1L));
//        assertTrue(gm.isLinked(3L, 2L));
//        assertTrue(gm.isLinked(3L, 3L));
//        assertTrue(gm.isLinked(3L, 4L));
//        assertFalse(gm.isLinked(3L, 10L));
//        assertFalse(gm.isLinked(3L, 11L));
//        assertFalse(gm.isLinked(3L, 12L));
//
//        assertTrue(gm.isLinked(4L, 1L));
//        assertTrue(gm.isLinked(4L, 2L));
//        assertTrue(gm.isLinked(4L, 3L));
//        assertTrue(gm.isLinked(4L, 4L));
//        assertFalse(gm.isLinked(4L, 10L));
//        assertFalse(gm.isLinked(4L, 11L));
//        assertFalse(gm.isLinked(4L, 12L));
//
//        assertTrue(gm.isLinked(10L, 10L));
//        assertTrue(gm.isLinked(10L, 11L));
//        assertTrue(gm.isLinked(10L, 12L));
//        assertFalse(gm.isLinked(10L, 1L));
//        assertFalse(gm.isLinked(10L, 2L));
//        assertFalse(gm.isLinked(10L, 3L));
//        assertFalse(gm.isLinked(10L, 4L));
//
//        assertTrue(gm.isLinked(11L, 10L));
//        assertTrue(gm.isLinked(11L, 11L));
//        assertTrue(gm.isLinked(11L, 12L));
//        assertFalse(gm.isLinked(11L, 1L));
//        assertFalse(gm.isLinked(11L, 2L));
//        assertFalse(gm.isLinked(11L, 3L));
//        assertFalse(gm.isLinked(11L, 4L));
//
//        assertTrue(gm.isLinked(12L, 10L));
//        assertTrue(gm.isLinked(12L, 11L));
//        assertTrue(gm.isLinked(12L, 12L));
//        assertFalse(gm.isLinked(12L, 1L));
//        assertFalse(gm.isLinked(12L, 2L));
//        assertFalse(gm.isLinked(12L, 3L));
//        assertFalse(gm.isLinked(12L, 4L));
//    }
//
//
//}
