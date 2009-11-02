

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package utiltest


import junit.framework.Assert._


class OrTest {

    import mada.util.||

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

    def nested[T <% String || Int](t: Option[T]): Int = t match {
       case Some(x: String) => 0
       case Some(x: Int) => 1
       case _ => 2
    }

    def testNested: Unit = {
        assertEquals(0, nested(Some("wow")))
        assertEquals(1, nested(Some(5)))
        //nested(Some('c'))
        ()
    }
}
