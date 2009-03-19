

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._
import madatest.vec.detail.Example._


class ReadOnlyTest {
    def testTrivial: Unit = {
        val v = Vector.from(Array(0,18,14,17)).readOnly
        var thrown = false
        try {
            v.sort
        } catch {
            case _: Vector.NotWritableException[_] => thrown = true
        }
        assertTrue(thrown)
    }
}