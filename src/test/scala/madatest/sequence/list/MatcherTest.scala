

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence._
import junit.framework.Assert._


class MatcherTest {

    def testLazy: Unit = {
        val x :: xs = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2 #:: 3 #:: 4 #:: 5 #:: Nil, xs())
    }

    def testStrict: Unit = {
        val x #:: y #:: ys = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2, y)
        assertEquals(3 #:: 4 #:: 5 #:: Nil, ys)
    }

    def testJumble: Unit = {
        val x #:: y #:: (z :: zs) = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2, y)
        assertEquals(3, z)
        assertEquals(4 #:: 5 #:: Nil, zs())
    }

}
