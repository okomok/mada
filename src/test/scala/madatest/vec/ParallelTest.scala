

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class ParallelTest {
    def testFusion: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        assertEquals(e, v.parallel(4).parallel(3).parallel(1).map(_ + 1))
    }
/*
    def testUnparallel: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        val pv = v.parallel(4).parallel(3)
        assertTrue(pv.isParallel)
        val upv = pv.unparallel
        assertFalse(upv.isParallel)
        val uupv = pv.unparallel.unparallel
        assertFalse(uupv.isParallel)
    }
*/
    def foo(x: Any): Unit = {
        //println(x)
        ()
    }

    def testParaPara: Unit = {
        for (i <- (0 until 100)) {
            // println(i)
            val z = Vector.range(1, 10).parallel.flatMap {
                case i => Vector.range(1, i).parallel.filter{ j => i+j >= 5 }.parallel.map{ case j => (i, j) }
            }
            ()
        }
        ()
    }
}
        // java.lang.AssertionError: assertion failed: receive from channel belonging to other actor
        // http://www.nabble.com/Actors-Break-Futures-td13813999.html

