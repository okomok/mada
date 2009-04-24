

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
//import junit.framework.Assert._


class BooleanTest {
    def testTrivial(off: Int): Unit = {
        assertEquals[`true`, `true`]
        assertEquals[`false`, `if`[`true`, `false`, `true`]]
        assertEquals[`false`, `if`[`false`, `true`, `false`]]
    }
}
