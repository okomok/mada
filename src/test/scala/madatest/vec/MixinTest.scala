

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._
import madatest.vec.detail.Example._


class MixinTest {
    def testTrivial: Unit = {
        val v = Vector.from(Array(0,18,14,17)).mixin(Vector.Mixin.readOnly)
        var thrown = false
        try {
            v.clone.sort
        } catch {
            case _: Vector.NotWritableException[_] => thrown = true
        }
        assertTrue(thrown)
    }
}
