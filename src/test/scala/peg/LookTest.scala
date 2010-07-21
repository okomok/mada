

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._


class LookTest extends org.scalatest.junit.JUnit3Suite {
    def testAhead: Unit = {
        assertTrue("abc" >> ~"def" >> "def" matches "abcdef")
        assertTrue("abc" >> ~(dot.*) >> "def" matches "abcdef")
        assertTrue("abc" >> !"xxx" >> "def" matches "abcdef")
    }

    def testBehind: Unit = {
        assertTrue("abc" >> "abc".<=~ >> "def" matches "abcdef")
        assertTrue("abc" >> "xxx".<=! >> "def" matches "abcdef")
    }

    def testBack: Unit = {
        assertTrue("abc" >> "cba".<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> ("cba" >> end).<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> dot.*.<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> "abc".<<! >> "defg" matches "abcdefg")

        assertTrue("abc" >> ("d".<=~ >> "cb" >> ~"a").<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> ("de".<<~ >> "cb" >> ~"a").<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> regex("(?<=d)cb(?=a)").<<~ >> "defg" matches "abcdefg")
    }
}
