

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
import junit.framework.Assert._


class BoolTest {
    def testTrivial(off: Int): Unit = {
        AssertEquals[True, True]
        AssertEquals[False, If[True, False, True]]
        AssertEquals[False, If[False, True, False]]
    }
}
