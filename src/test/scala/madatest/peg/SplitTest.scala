

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg
import junit.framework.Assert._
import mada.Peg.Compatibles._

import mada.{Vector, vector}


class SplitTest {
    def testTrivial: Unit = {
        assertEquals(
            Vector(vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            vector.fromIterable(Peg.from(":").split(vector.from("ab:cdef:gh"))) )

        assertEquals(
            Vector(vector.from(""), vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            vector.fromIterable(Peg.from(":").split(vector.from(":ab:cdef:gh"))) )

        assertEquals(
            Vector(vector.from(""), vector.from("ab"), vector.from("cdef"), vector.from("gh")),
            vector.fromIterable(Peg.from(":").split(vector.from(":ab:cdef:gh:"))) )

        assertEquals(
            Vector(vector.from("ab"), vector.from(""), vector.from("cdef"), vector.from("gh"), vector.from("")),
            vector.fromIterable(Peg.from(":").split(vector.from("ab::cdef:gh::"))) )
    }

    def testBound: Unit = {
        assertEquals(
            Vector(vector.from("ab")),
            vector.fromIterable(Peg.from(":").split(vector.from("ab"))) )

        assertEquals(
            Vector(vector.from("ab")),
            vector.fromIterable(Peg.from(":").split(vector.from("ab:"))) )

        assertEquals(
            Vector(vector.from(""), vector.from("ab")),
            vector.fromIterable(Peg.from(":").split(vector.from(":ab:"))) )

        assertTrue(
            Peg.from(":").split(vector.from("")).isEmpty )
    }

    def testRegexBehavior(off: Int): Unit = {
        println(vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef")))
        println(vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef:")))
        println(vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef")))
        println(vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef:")))
    }
}
