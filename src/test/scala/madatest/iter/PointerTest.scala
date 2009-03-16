

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import mada.Iterables.Pointer
import junit.framework.Assert._


class PointerTest {
    def testTrivial: Unit = {
        val A1 = Iterables(1,6,7,10,14,17)
        assertNotSame(A1.elements, A1.elements)
//        printPt(Pointer.from(A1.elements))
        assertEqualsPtIt(Pointer.from(A1.elements), A1.elements)
        assertTrue( Iterables.equal(Iterables.by(Pointer.toIterator(Pointer.from(A1.elements))), A1) )
    }

    def assertEqualsPtIt[A](pt: Pointer[A], it: Iterator[A]): Unit = {
        while (it.hasNext && !pt.isEnd) {
            assertEquals(it.next, ~pt)
            pt.++
        }
        assertTrue(!it.hasNext && pt.isEnd)
    }

    def printPt[A](pt: Pointer[A]): Unit = {
        while (!pt.isEnd) {
            println(~pt)
            pt.++
        }
    }
}
