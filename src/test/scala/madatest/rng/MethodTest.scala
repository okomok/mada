
package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.Size._
import mada.rng.Method._
import junit.framework.Assert._
import mada.rng.Begin._
import mada.rng.End._


class MethodTest {
    def testTrivial: Unit = {
        assertEquals(4L, from(Array(0,1,2,3)).method(_./.size./)./)
        assertEquals(from(Array(0,1,2,3))./, from(Array(0,1,2,3)).method(_./.begin./, _./.end./)./)
    }
}
