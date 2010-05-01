

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import junit.framework.Assert._


class EnvTest {
    def testTrivial: Unit = {
        mada.blend.`if`[Unit, mada.isDebug] {
            assertTrue(mada.isDebug)
        } `else` {
            assertFalse(mada.isDebug)
        }
    }
}
