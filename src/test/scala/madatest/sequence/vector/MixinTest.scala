

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._


class MixinTest {
    def testTrivial: Unit = {
        val v = vector.from(Array(0,18,14,17)).mix(vector.Mixin.readOnly)
        var thrown = false
        try {
            v.copy.sort
        } catch {
            case _: vector.NotWritableException[_] => thrown = true
        }
        assertTrue(thrown)
    }
}
