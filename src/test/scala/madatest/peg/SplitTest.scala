

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package pegtest


import mada.{Peg, peg}
import junit.framework.Assert._
import mada.peg.Compatibles._

import mada.sequence.{Vector, vector}


class SplitTest {
    def testTrivial: Unit = {
        assertEquals(
            mada.sequence.Iterative(vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            peg.from(":").split(vector.from("ab:cdef:gh")) )

        assertEquals(
            mada.sequence.Vector(vector.from(""), vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            peg.from(":").split(vector.from(":ab:cdef:gh")) )

        assertEquals(
            mada.sequence.Iterative(vector.from(""), vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            peg.from(":").split(vector.from(":ab:cdef:gh:")) )

        assertEquals(
            mada.sequence.Vector(vector.from("ab"), vector.from(""), vector.from("cdef"), vector.from("gh"), vector.from("")),
            peg.from(":").split(vector.from("ab::cdef:gh::")) )
    }

    def testBound: Unit = {
        assertEquals(
            mada.sequence.Vector(vector.from("ab")),
            peg.from(":").split(vector.from("ab")) )

        assertEquals(
            mada.sequence.Iterative(vector.from("ab")),
            peg.from(":").split(vector.from("ab:")) )

        assertEquals(
            mada.sequence.Vector(vector.from(""), vector.from("ab")),
            peg.from(":").split(vector.from(":ab:")) )

        assertTrue(
            peg.from(":").split(vector.from("")).isEmpty )
    }

    def testRegexBehavior(off: Int): Unit = {
        println(vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef")))
        println(vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef:")))
        println(vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef")))
        println(vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef:")))
    }
}
