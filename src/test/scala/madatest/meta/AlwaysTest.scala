

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class AlwaysTest {

    trait Strong extends Object
    final class so extends Strong

    def testTrivial: Unit = {
        assertEquals[string, always[string]#apply0[void]]
        assertEquals[string, always[string]#apply1[so]]
        assertEquals[string, always[string]#apply2[int, so]]
        assertEquals[string, always[string]#apply3[int, so, so]]
    }
}
