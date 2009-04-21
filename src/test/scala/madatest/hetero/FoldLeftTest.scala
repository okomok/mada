

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Hetero
import junit.framework.Assert._


class FoldLeftTest {
    def testTrivial: Unit = {
    }

    def testTypeSafe: Unit = {
        val h = Hetero.Iterable.from(Tuple(2, "hello"))
        val i: Int = h.elements.head
        assertEquals(6, i * 3)
        val j: String = h.elements.toNext.head
        assertEquals("hello", j)
    }


    class IdFun extends Hetero.PolyFunction1 {
        type Apply[A] = A
        def apply[A](e: A): Any = e.asInstanceOf[A]
    }

    def testMap: Unit = {
        val h = Hetero.Iterable.from(Tuple(2, "hello"))
        val i: Int = h.map(new IdFun).elements.head
        assertEquals(6, i * 3)
        val j: String = h.map(new IdFun).elements.toNext.head
        assertEquals("hello", j)
    }
}
