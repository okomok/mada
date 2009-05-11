

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.blend
import mada.Env
import junit.framework.Assert._


class EnvTest {
    def testTrivial: Unit = {
        blend.`if`[Unit, Env.isDebug] {
            assertTrue(Env.isDebug)
        } `else` {
            assertFalse(Env.isDebug)
        }
    }
}
