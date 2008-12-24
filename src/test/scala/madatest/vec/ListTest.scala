

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import mada.Vector.fromArray
import junit.framework.Assert._
import detail.Example._


class ListTest {
    def testTo: Unit = {
        val l = fromArray(example1).toList
        var i = 0
        l.foreach({ (e: Int) =>
            assertEquals(example1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = Vector.fromList(example1.toList)
        detail.TestVectorReadOnly(example1, ac)
    }
}
