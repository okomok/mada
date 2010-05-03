

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest


import com.github.okomok.mada

import mada.meta
import mada.blend._
import junit.framework.Assert._


class TimesTest {
    def testTrivial: Unit = {
        var i = 0
        times[meta._6N#increment] {
            i += 1
        }
        assertEquals(7, i)
    }

    def testTrivial2: Unit = {
        var i = 11
        timesBy[meta._6N#increment] { j =>
            i += j
        }
        assertEquals(11+0+1+2+3+4+5+6, i)
    }
}
