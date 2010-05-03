

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class UnfoldTest {
    def testRight: Unit = {
        val E1 = iterative.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), E1)
    }

    def testIterate: Unit = {
        val E1 = iterative.iterate(1){ 2 * _ }
        assertEquals(iterative.Of(1,2,4,8,16,32,64,128,256,512), E1.take(10))
    }
}
