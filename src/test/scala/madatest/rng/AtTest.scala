
package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.At._
import junit.framework.Assert._


class AtTest {
    def testTrivial: Unit = {
        assertEquals(2, from(Array(0,1,2,3)).at(2)./)
        assertEquals(0, from(Array(0,1,2,3)).at(0)./)
        assertEquals(3, from(Array(0,1,2,3)).at(3)./)
    }
}
