

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import junit.framework.Assert._
import madatest.vectortest.detail.Example._


class MixinTest {
    def testTrivial: Unit = {
        val v = vector.from(Array(0,18,14,17)).mixin(vector.Mixin.readOnly)
        var thrown = false
        try {
            v.clone.sort
        } catch {
            case _: vector.NotWritableException[_] => thrown = true
        }
        assertTrue(thrown)
    }
}