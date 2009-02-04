

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Products._
import junit.framework.Assert._


class ProductsTest {
    def testTrivial: Unit = {
        val r1 = RangeVal(10, 12)
        val r2 = RangeVal(10, 12)
        val r3 = RangeVal(11, 13)

        AssertNotEquals(r1.hashCodeOfRef, r1.hashCode)

        assertEquals(r1, r2)
        assertEquals(r1.hashCode, r2.hashCode)

        AssertNotEquals(r1, r3)
        AssertNotEquals(r1.hashCode, r3.hashCode)

        assertEquals(r1.hashCodeOfRef, r2.hashCodeOfRef)
        AssertNotEquals(r1.hashCodeOfRef, r3.hashCodeOfRef)
    }
}
