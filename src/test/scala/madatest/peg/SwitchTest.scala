

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._

import mada.Pegs.Compatibles._
import mada.Pegs._


class SwitchTest {
    def testTrivial: Unit = {
        val g = switch("z" inCase 'e', "gh" inCase 'f' , "abc" inCase 'q')
        assertTrue("RR" >> g >> "LL"  matches "RRezLL")
        assertTrue("RR" >> g >> "LL"  matches "RRfghLL")
        assertTrue("RR" >> g >> "LL"  matches "RRqabcLL")
    }
}
