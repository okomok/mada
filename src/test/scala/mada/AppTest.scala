package mada;

import junit.framework._;
import Assert._;

object AppTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[AppTest]);
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite);
    }
}


class Identity[T] {
  type Type = T
}

class Super {
  def foo(a: AppTest) = {}
}

/*
object Foo {
  def id[T](x: T) = new Identity[T]#Type() { }
}
*/

/**
 * Unit test for simple App.
 */
class AppTest extends TestCase("app") {

    /**
     * Rigourous Tests :-)
     */
    def testOK() = assertTrue(true);
    def testKO() = assertTrue(true);
    /*
    val x: Identity[Integer]#Type = 3;

    def testType() = assertTrue(x == 3);
*/


}
