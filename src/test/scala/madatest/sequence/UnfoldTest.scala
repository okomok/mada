

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class UnfoldTest {
    def testRight: Unit = {
        val E1 = sequence.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertEquals(sequence.Of(10,9,8,7,6,5,4,3,2,1), E1)
    }

    def testIterate: Unit = {
        val E1 = sequence.iterate(1){ 2 * _ }
        assertEquals(sequence.Of(1,2,4,8,16,32,64,128,256,512), E1.take(10))
    }
}
