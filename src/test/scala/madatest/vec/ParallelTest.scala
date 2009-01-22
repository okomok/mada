

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
        assertTrue(pv.isInstanceOf[vec.ParallelVector[_]])
        val upv = pv.unparallel
        assertFalse(upv.isInstanceOf[vec.ParallelVector[_]])
        val uupv = pv.unparallel.unparallel
        assertFalse(uupv.isInstanceOf[vec.ParallelVector[_]])
    }
*/
    def foo(x: Any): Unit = {
        //println(x)
        ()
    }

    def untestFor: Unit = {
        val w = for {
            i <- Vector.range(1, 10).parallely
            j <- Vector.range(1, i).parallely
            if (i + j >= 5)
        } yield (i + j)

        val s = w.toString
        foo(s)

        ()
    }

    def testParaPara: Unit = {
        // java.lang.AssertionError: assertion failed: receive from channel belonging to other actor
        // http://www.nabble.com/Actors-Break-Futures-td13813999.html

        for (i <- (0 until 100)) {
            //println(i)

        val z = Vector.range(1, 10).parallel.flatMap {
            case i => Vector.range(1, i).parallel.filter{ j => i+j >= 5 }.parallel.map{ case j => (i, j) }
        }
/*
        println("testParaPara")
        val w = for {
            i <- Vector.range(1, 10).parallel//.parallely//.parallel
            j <- Vector.range(1, i).parallel//.parallely
            if (i + j >= 5)
        } yield (i + j)
        println("end testParaPara")
*/
        ()
        }
        ()
    }

    def testForeach: Unit = {
        for {
            i <- Vector.range(1, 10).parallely
            j <- Vector.range(1, i).parallely
            if (i + j >= 5)
        } foo (i + j)
    }
}
