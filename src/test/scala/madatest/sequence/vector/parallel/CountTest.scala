

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest; package paralleltest


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class CountTest {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from("a813a91ng8a89a8")
        assertEquals(4, v.parallel.count(_ == 'a'))
        assertEquals(4, v.parallelBy(1000).count(_ == 'a'))
        assertEquals(4, v.parallelBy(6).count(_ == 'a'))

        assertEquals(0, v.parallel.count(_ == 'z'))
        assertEquals(0, v.parallelBy(1000).count(_ == 'z'))
        assertEquals(0, v.parallelBy(6).count(_ == 'z'))
    }
}


class CountNoThreadsTest extends NoBenchmark {
    override def run = {
        val a = longSample1.count({e => longCalc; e % 2 == 0})
        ()
    }
}

class CountParallelCountTest extends NoBenchmark {
    override def run = {
        val a = longSample1.parallel.count({e => e % 2 == 0})
        //val a = longSample1.divide(mada.sequence.vector.parallel.DefaultGrainSize(longSample1)).parallel.map({ w => w.count(_ % 2 == 0) }).unparallel.reduce(_ + _)
        ()
    }
}

// too slow.
class CountParallelEachTest extends NoBenchmark {
    override def run = {
        val n = new java.util.concurrent.atomic.AtomicInteger(0)
        longSample1.parallelBy(longSample1.defaultGrainSize).each({ e => if (e % 2 == 0) n.incrementAndGet })
        n.get
        ()
    }
}

