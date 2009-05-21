

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.bound
import junit.framework.Assert._


class BoundTest {

    import bound.||

    def intOrString[T <% Int || String](x: T): Int = x match {
        case x: Int => x
        case x: String => 777
        case _ => fail; 9999
    }

    def testOr2: Unit = {
        assertEquals(10, intOrString(10))
        assertEquals(777, intOrString("hello"))
        //intOrString(3.4)
        ()
    }

    def manyOr[T <% Int || String || Char || Boolean](x: T): Int = x match {
        case x: Int => x
        case x: String => 777
        case x: Char => 9
        case x: Boolean => 1
        case _ => fail; 9999
    }

    def testOr4: Unit = {
        assertEquals(10, manyOr(10))
        assertEquals(777, manyOr("hello"))
        assertEquals(9, manyOr('a'))
        assertEquals(1, manyOr(false))
        //manyOr(Array(3,4))
        ()
    }
}
