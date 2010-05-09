

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class JavaTest extends junit.framework.TestCase {

    def testJioReader: Unit = {
        val arr = mada.sequence.Vector('a','b','c').toArray[Char]
//        val arr = Array('a','b','c')
        val tr = iterative.from(new java.io.CharArrayReader(arr))
        assertEquals(iterative.Of('a','b','c'), tr)
        assertEquals(iterative.Of('a','b','c'), tr) // traverse again.
    }

}
