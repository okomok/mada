

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class SwitchTest {
    def testTrivial: Unit = {
        val g = Switch("z" inCase 'e', "gh" inCase 'f' , "abc" inCase 'q')
        assertTrue("RR" >> g >> "LL"  matches "RRezLL")
        assertTrue("RR" >> g >> "LL"  matches "RRfghLL")
        assertTrue("RR" >> g >> "LL"  matches "RRqabcLL")
    }
}
