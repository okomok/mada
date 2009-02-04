

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vectors

import mada.Pegs.Compatibles._
import mada.Pegs._


class IcaseTest {
    def testTrivial: Unit = {
        assertTrue(icase("AbcDeFG") matches "ABcDefg")
        assertTrue(icase("AbcDeFG") matches "abCdEfG")
    }
}
