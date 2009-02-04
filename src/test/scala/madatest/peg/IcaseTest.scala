

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector

import mada.Peg.Compatibles._
import mada.Peg._


class IcaseTest {
    def testTrivial: Unit = {
        assertTrue(icase("AbcDeFG") matches "ABcDefg")
        assertTrue(icase("AbcDeFG") matches "abCdEfG")
    }
}
