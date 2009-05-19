

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada._
import mada.vector.fromArray
import junit.framework.Assert._
import detail.Example._


class IteratorTest {
    def testTo: Unit = {
        val it = fromArray(example1).elements
        var i = 0
        it.foreach({ (e: Int) =>
            assertEquals(example1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = vector.fromIterable(example1)
        detail.TestVectorReadOnly(example1, ac)
    }
}
