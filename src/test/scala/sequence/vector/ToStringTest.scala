

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.sequence._


class ToStringTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val k: Vector[Char] = vector.from("abcde")
       // println(k.toString)

        assertEquals(0, "[a, b, c, d, e]".compareTo(vector.from("abcde").toString))
        assertEquals(0, "[a]".compareTo(vector.from("a").toString))
        assertEquals(0, "[]".compareTo(vector.from("").toString))
    }
}
