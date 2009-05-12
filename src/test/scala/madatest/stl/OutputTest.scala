

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.stl


import mada.vector._
import mada.{Vector, vector}
import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class OutputTest {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        var count = 0
        mada.Stl.copy(v, 0, 10)(mada.Stl.outputBy({ (e: Any) => count += 1 }), 999)
        assertEquals(10, count)
    }

    def testCounter: Unit = {
        val v = fromArray(example1)
        val ct = new mada.Stl.OutputCounter
        mada.Stl.copy(v, 0, 10)(ct, 999)
        assertEquals(10, ct.count)
    }

    def testCounter2: Unit = {
        val v = fromArray(example1)
        val ct = new mada.Stl.OutputCounter(1000)
        mada.Stl.copy(v, 0, 10)(ct, 999)
        assertEquals(1010, ct.count)
    }
}
