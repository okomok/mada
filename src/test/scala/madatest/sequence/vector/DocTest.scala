

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.vector
import junit.framework.Assert._

class DocTest {
    def testTrivial: Unit = {
        val v = vector.of(0,1,2,3,4)
        v.parallel.map(_ + 10).parallel.seek(_ == 13) match {
            case Some(e) => assertEquals(13, e)
            case None => fail("doh")
        }

        val i = new java.util.concurrent.atomic.AtomicInteger(0)
        v.parallel.each {
            _ => i.incrementAndGet
        }
        assertEquals(5, i.get)
    }
}
