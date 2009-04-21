

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hiter


import mada.HeteroIterable
import junit.framework.Assert._


class FoldLeftTest {
    def testTrivial: Unit = {
        val x = HeteroIterable.from(Tuple(1, "hello")).foldLeft("Z") { (b, a) =>
            b.toString + a.toString
        }
        assertEquals("Z1hello", x)
    }

    def testTypeSafe: Unit = {
        val h = HeteroIterable.from(Tuple(2, "hello"))
        val i = h.elements.head
        assertEquals(6, i * 3)
        val j = h.elements.toNext.head
        assertEquals("hello", j)
    }
}
