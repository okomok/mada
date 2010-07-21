

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package defecttest; package forwardingjcl


import junit.framework.Assert._


class ForwardingJclTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
//        val fc = java.util.Collections.unmodifiableCollection(new java.util.ArrayList[Int]) // can't forward equals.
//        val l = new java.util.ArrayList[Int]
//        assertEquals(fc == l, l == fc)
    }
}
