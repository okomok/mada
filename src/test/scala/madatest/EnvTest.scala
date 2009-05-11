

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


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
