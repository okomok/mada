

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative._
import junit.framework.Assert._


class ByNameTest {
    var c = 0

    def testLazy: Unit = {
        c = 0
        val t = byLazy(makeT)

        val s1 = t.map(_ + 1)
        assertEquals(1, c)
        val s2 = t.map(_ + 1)
        assertEquals(1, c)

        val a = Of(2,3,4)
        assertEquals(a, s1)
        assertEquals(a, s2)
    }

    def testName: Unit = {
        c = 0
        val t = byName(makeT)

        val s1 = t.map(_ + 1)
        assertEquals(1, c)
        val s2 = t.map(_ + 1)
        assertEquals(2, c)

        val a = Of(2,3,4)
        assertEquals(a, s1)
        assertEquals(a, s2)
    }

    def makeT = {
        c += 1
        Of(1,2,3)
    }
}
