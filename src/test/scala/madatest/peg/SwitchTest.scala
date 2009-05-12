

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._

import mada.peg.compatibles._
import mada.peg._


class SwitchTest {
    def testTrivial: Unit = {
        val g = switch("z" inCase 'e', "gh" inCase 'f' , "abc" inCase 'q')
        assertTrue("RR" >> g >> "LL"  matches "RRezLL")
        assertTrue("RR" >> g >> "LL"  matches "RRfghLL")
        assertTrue("RR" >> g >> "LL"  matches "RRqabcLL")
    }
}
