

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg
import junit.framework.Assert._
import mada.Peg.Compatibles._

import mada.Vector


class SplitTest {
    def testTrivial: Unit = {
        assertEquals(
            Vector(Vector.from("ab"), Vector.from("cdef"), Vector.from("gh")),
            Vector.fromIterable(Peg.from(":").split(Vector.from("ab:cdef:gh"))) )

        assertEquals(
            Vector(Vector.from(""), Vector.from("ab"), Vector.from("cdef"), Vector.from("gh")),
            Vector.fromIterable(Peg.from(":").split(Vector.from(":ab:cdef:gh"))) )

        assertEquals(
            Vector(Vector.from(""), Vector.from("ab"), Vector.from("cdef"), Vector.from("gh")),
            Vector.fromIterable(Peg.from(":").split(Vector.from(":ab:cdef:gh:"))) )

        assertEquals(
            Vector(Vector.from("ab"), Vector.from(""), Vector.from("cdef"), Vector.from("gh"), Vector.from("")),
            Vector.fromIterable(Peg.from(":").split(Vector.from("ab::cdef:gh::"))) )
    }

    def testBound: Unit = {
        assertEquals(
            Vector(Vector.from("ab")),
            Vector.fromIterable(Peg.from(":").split(Vector.from("ab"))) )

        assertEquals(
            Vector(Vector.from("ab")),
            Vector.fromIterable(Peg.from(":").split(Vector.from("ab:"))) )

        assertEquals(
            Vector(Vector.from(""), Vector.from("ab")),
            Vector.fromIterable(Peg.from(":").split(Vector.from(":ab:"))) )

        assertTrue(
            Peg.from(":").split(Vector.from("")).isEmpty )
    }

    def testRegexBehavior(off: Int): Unit = {
        println(Vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef")))
        println(Vector.from(java.util.regex.Pattern.compile(":").split("ab:cd:ef:")))
        println(Vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef")))
        println(Vector.from(java.util.regex.Pattern.compile(":").split(":ab:cd:ef:")))
    }
}
