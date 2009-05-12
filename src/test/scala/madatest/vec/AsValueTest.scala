

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.Vector
import junit.framework.Assert._


class AsValueTest {
    def testTrivial: Unit = {
        val a = Vector.range(3, 10)
        val b = Vector.range(3, 10)
        val c = Vector.range(4, 5)
        assertNotSame(a, b)
        assertEquals(a, b)
        AssertNotEquals(a, c)

        // Note: v.readOnly.hashCode can't be lazy because element itself can be mutable.
        assertEquals(a.hashCode, b.hashCode)
        AssertNotEquals(a.hashCode, c.hashCode)
    }

    def testMatching: Unit = {
        val a: Any = Vector(1,2,3,4,5)
        assertEquals(Vector.range(1, 6), a)

        val Vector(x1,2,x3,4,x5) = a
        assertEquals(1, x1)
        assertEquals(3, x3)
        assertEquals(5, x5)

        a match {
            case Vector(x1,x2) => fail("doh")
            case Vector(x1,2,x3,4,x5) => ()
            case _ => fail("doh")
        }
        ()
    }

    def testNestedMatching: Unit = {
        val a = Vector.range(4, 10)
        val Vector.Region(Vector(4,5,6,7,8,9), 5, 9) = a(5, 9)
        ()
    }
}
