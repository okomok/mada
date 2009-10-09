

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence._
import junit.framework.Assert._


class FoldTest {

    def testRight: Unit = {
        val A: List[Int] = (1 :: 3 :: 4 :: Nil).cycle // infinite
        val B: List[Int] = A.foldRight(Nil.of[Int])(new Cons(_, _))
        assertEquals(A.take(30), B.take(30))
    }

}
