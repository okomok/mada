

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence._
import junit.framework.Assert._


class FoldTest {

    def testRight: Unit = {
        val A: List[Int] = (1 :: 3 :: 4 :: Nil).cycle // infinite
        val B: List[Int] = A.foldRight(NilOf[Int]){ (x, xs) => new Cons(x, xs) }
        assertEquals(A.take(30), B.take(30))
    }

}
