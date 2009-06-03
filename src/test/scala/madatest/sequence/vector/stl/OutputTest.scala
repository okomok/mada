

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.stltest


import mada.sequence.vector._
import mada.sequence.{Vector, vector}
import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class OutputTest {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        var count = 0
        mada.sequence.vector.stl.copy(v, 0, 10)(mada.sequence.vector.stl.outputBy({ (e: Any) => count += 1 }), 999)
        assertEquals(10, count)
    }

    def testCounter: Unit = {
        val v = fromArray(example1)
        val ct = new mada.sequence.vector.stl.OutputCounter
        mada.sequence.vector.stl.copy(v, 0, 10)(ct, 999)
        assertEquals(10, ct.count)
    }

    def testCounter2: Unit = {
        val v = fromArray(example1)
        val ct = new mada.sequence.vector.stl.OutputCounter(1000)
        mada.sequence.vector.stl.copy(v, 0, 10)(ct, 999)
        assertEquals(1010, ct.count)
    }
}
