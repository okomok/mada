

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package detail


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


object TestEmpty {
    def apply[A](v: Vector[A]) {
        assertEquals(0, v.size)
    }
}
