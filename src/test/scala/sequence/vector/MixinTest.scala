

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._


class MixinTest extends org.scalatest.junit.JUnit3Suite {
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
