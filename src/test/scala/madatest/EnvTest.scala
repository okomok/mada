

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Env
import junit.framework.Assert._


class DebugTest {
    def testTrivial: Unit = {
        val j = mada.Env.isDebug
        val k = mada.isDebug_
        ()
    }
}
