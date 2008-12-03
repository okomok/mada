
package madatest.rng.jcl


import mada.rng.jcl.ArrayList
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._
import mada.rng.jcl.ArrayListCompatible._


class ArrayListTest {
    def testTrivial = {
        detail.TestRandomAccessReadWrite(example1, from(ArrayList(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)).eval)
    }
}
