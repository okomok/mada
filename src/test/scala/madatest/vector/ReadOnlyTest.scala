

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import junit.framework.Assert._
import madatest.vectortest.detail.Example._


class ReadOnlyTest {
    def testTrivial: Unit = {
        val v = vector.from(Array(0,18,14,17)).readOnly
        var thrown = false
        try {
            v.sort
        } catch {
            case _: vector.NotWritableException[_] => thrown = true
        }
        assertTrue(thrown)
    }
}