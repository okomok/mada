

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.stl


import mada.Vector._
import mada.Vector
import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class OutputTest {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        val ct = new mada.vec.stl.OutputCounter()
        var count = 0
        mada.vec.stl.Copy(v, 0, 10, mada.vec.stl.Output({ (e: Any) => count += 1 }), 999)
        assertEquals(10, count)
    }

    def testCounter: Unit = {
        val v = fromArray(example1)
        val ct = new mada.vec.stl.OutputCounter()
        mada.vec.stl.Copy(v, 0, 10, ct, 999)
        assertEquals(10L, ct.count)
    }

    def testCounter2: Unit = {
        val v = fromArray(example1)
        val ct = new mada.vec.stl.OutputCounter(1000)
        mada.vec.stl.Copy(v, 0, 10, ct, 999)
        assertEquals(1010L, ct.count)
    }
}
