

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.para


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class ContainsTest {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from("abc1d3ef5g4qu67")

        assertTrue(v.parallel.contains('a'))
        assertTrue(v.parallel.contains('e'))
        assertTrue(v.parallel.contains('g'))
        assertFalse(v.parallel.contains('z'))

        assertTrue(v.parallel(1000).contains('a'))
        assertTrue(v.parallel(1000).contains('e'))
        assertTrue(v.parallel(1000).contains('g'))
        assertFalse(v.parallel(1000).contains('z'))

        assertTrue(v.parallel(6).contains('a'))
        assertTrue(v.parallel(6).contains('e'))
        assertTrue(v.parallel(6).contains('g'))
        assertFalse(v.parallel(6).contains('z'))

        ()
    }
}


class ContainsNoThreadsTest extends NoBenchmark {
    override def run = {
        val a = longSample1.contains(405)
        ()
    }
}

class ContainsParallelTest extends NoBenchmark {
    override def run = {
        val a = longSample1.parallel.contains(405)
        ()
    }
}
