

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.stltest


import mada.vector._
import mada.{Vector, vector}
import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class OutputTest {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        var count = 0
        mada.vector.stl.copy(v, 0, 10)(mada.vector.stl.outputBy({ (e: Any) => count += 1 }), 999)
        assertEquals(10, count)
    }

    def testCounter: Unit = {
        val v = fromArray(example1)
        val ct = new mada.vector.stl.OutputCounter
        mada.vector.stl.copy(v, 0, 10)(ct, 999)
        assertEquals(10, ct.count)
    }

    def testCounter2: Unit = {
        val v = fromArray(example1)
        val ct = new mada.vector.stl.OutputCounter(1000)
        mada.vector.stl.copy(v, 0, 10)(ct, 999)
        assertEquals(1010, ct.count)
    }
}
