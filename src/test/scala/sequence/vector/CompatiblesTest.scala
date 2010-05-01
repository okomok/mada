

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.fromArray
import junit.framework.Assert._
import mada.sequence.{Vector, vector}
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class CompatiblesTest {
    def testCompile(x: Int): Unit = {
        takeIterable(makeVector.toSeq)
        takeVector(Array(1,2,3))
    }

    def takeVector[A](v: Vector[A]): Unit = { }
    def takeIterable[A](it: Iterable[A]): Unit = { }
    def makeVector: Vector[Char] = throw new Error()
}
