package mada;

import junit.framework._;
import Assert._;

import mada.cursor._;


object CursorTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[CursorTest]);
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite);
    }
}

/**
 * Unit test for simple App.
 */
class CursorTest extends TestCase("cursor.Cursor") {

    /**
     * Rigourous Tests :-)
     */
    def testOK() = assertTrue(true);
    def testKO() = assertTrue(false);
    

}
