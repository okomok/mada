

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.defecttest.forwardingjcl


import junit.framework.Assert._


class ForwardingJclTest {
    def testTrivial: Unit = {
//        val fc = java.util.Collections.unmodifiableCollection(new java.util.ArrayList[Int]) // can't forward equals.
//        val l = new java.util.ArrayList[Int]
//        assertEquals(fc == l, l == fc)
    }
}
