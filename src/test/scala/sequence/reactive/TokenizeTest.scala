

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._
import mada.sequence.iterative
import mada.Peg
import mada.sequence.Vector


class TokenizeTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val r = reactive.from(Vector.from("XXabcdXXabcdXX"))
        val out = new java.util.ArrayList[String]
        for (t <- r.tokenize(Peg.from("abcd"))) {
            out.add(t.stringize) // needs copy(to string or something).
        }
        assertEquals(Vector.Of("abcd", "abcd"), Vector.from(out))
    }

}
