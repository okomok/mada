

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package stacktest


import com.github.okomok.mada

import mada.stack
import junit.framework.Assert._


class ConversionTest extends junit.framework.TestCase {

    def testTrivial: Unit = {
        val j = new java.util.ArrayDeque[Int]
        val s = stack.from(j)
        /* toSome was rejected.
//        s.toSome.getFirst
        s.toSome.toSome
        */
        ()
    }

}
