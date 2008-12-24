

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import mada.Vector.fromArray
import junit.framework.Assert._
import detail.Example._


class StreamTest {
    def testTo: Unit = {
        val s = fromArray(example1).toStream
        var i = 0
        s.foreach({ (e: Int) =>
            assertEquals(example1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = Vector.fromStream(example1.toStream)
        detail.TestVectorReadOnly(example1, ac)
    }
}
