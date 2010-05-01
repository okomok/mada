

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class GroupByTest {
    def testTrivial: Unit = {
        val A = Iterative("abc", "ab", "abcde", "efg", "a", "abcd", "b")
        val B = A.groupBy(_.length)
        assertEquals(Vector("a", "b"), B.get(1).get.asVector)
        assertEquals(Vector("abc", "efg"), B.get(3).get.asVector)

        val x: Option[Vector[String]] = B.get(1)
        x.asVector // sucks.

        ()
    }
}
