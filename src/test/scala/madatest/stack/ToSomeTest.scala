

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.stacktest


import mada.stack
import junit.framework.Assert._


class ToSomeTest {

    def testTrivial: Unit = {
        val j = new java.util.ArrayDeque[Int]
        val s = stack.from(j)
//        s.toSome.getFirst
        s.toSome.toSome
        ()
    }

}
