

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import junit.framework.Assert._

import mada.peg.Compatibles._
import mada.peg._
import mada.sequence.vector



class SymbolSetTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val i = (vector.from("abc") >> symbolSet("to", "too", "tot", "tab", "so")).lookingAt(vector.from("abcto").nth).get
        assertEquals(5, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" >> symbolSet("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testMethods: Unit = {
        val set = symbolSet("to", "too", "tot", "tab", "so")
        //assertFalse(set.+=("to"))
        //assertTrue(set.-=("too"))
        ()
    }
}
