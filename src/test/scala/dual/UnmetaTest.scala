

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._
import nat.peano.Literal._
import nat.Operator._


class UnmetaTest extends org.scalatest.junit.JUnit3Suite {

    def testBoolean {
        type r = `true`
        assertEquals(`true`, unmeta[r])
        type s = `false`
        assertEquals(`false`, unmeta[s])
    }

    def testNatPeano {
        type r = _1 + _3 + _4
        assertEquals(_8, unmeta[r])
        assertEquals(8, unmeta[r].undual)
    }

    def testUnit {
        type r = Unit
        assertEquals(Unit, unmeta[r])
    }


}
