

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.paralleltest


import mada.sequence.{Vector, vector}

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class SeekTest {
    def testTrivial: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals("kl", v.parallel.seek(_ == "kl").get)
    }

    def testTrivial2: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals("kl", v.parallel(1).seek(_ == "kl").get)
    }

    def testNull: Unit = {
        val v: Vector[String] = vector.Of("ab", "cde", null.asInstanceOf[String], "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals(null.asInstanceOf[String], v.parallel(1).seek((_: String) eq null).get)
    }

    def testNotFound: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertTrue(v.parallel.seek(_ == "xyz").isEmpty)
    }
}
