

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence._
import junit.framework.Assert._


class UnfoldTest {
    def testRight: Unit = {
        val E1 = list.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1).toList, E1)
    }

    def testIterate: Unit = {
        val E1 = list.iterate(1){ 2 * _ }
        assertEquals(iterative.Of(1,2,4,8,16,32,64,128,256,512).toList, E1.take(10))
    }

    def testRepeat: Unit = {
        val E1 = list.repeat(3)
        assertEquals(iterative.Of(3,3,3,3,3,3,3,3,3,3).toList, E1.take(10))
    }

    def testReplicate: Unit = {
        val E1 = list.replicate(10, 3)
        assertEquals(iterative.Of(3,3,3,3,3,3,3,3,3,3).toList, E1)
    }
}
