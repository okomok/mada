

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._

import mada.peg.Compatibles._
import mada.peg._


class CapturingGroupsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val c = new CapturingGroups[String, Char]
        val p = ("abcd" >> c("name", "EFG") >> "hi" >> c("name"))
        assertTrue(p matches "abcdEFGhiEFG")
    }
}
