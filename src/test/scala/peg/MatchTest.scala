

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._
import mada.sequence.Vector


class MatchTest extends org.scalatest.junit.JUnit3Suite {
    def testYes {
        val p = "abc" >> "def"
        Vector.from("abcdef") match {
            case p(x) => assertEquals(Vector.from("abcdef"), x)
            case _ => fail("doh")
        }
    }

    def testNo {
        val p = "abc" >> "de"
        Vector.from("abcdef") match {
            case p(x) => fail("doh")
            case _ => ()
        }
    }
}
