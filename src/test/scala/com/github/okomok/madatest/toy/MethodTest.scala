

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package methodtest


import com.github.okomok.mada

import junit.framework.Assert._


object foo {
    def apply[A](e: A) = e
}


class MethodTezt {

    def testTrivial: Unit = {
        assertEquals(3, (foo(_: Int))(3))
    }

}
