

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


object AssertThrows {
    def apply(body: => Unit): Unit = {
        var thrown = false
        try {
            body
        } catch {
            case _ => thrown = true
        }

        if (!thrown) {
            fail("not thrown")
        }
    }
}


class AssertThrowsTest {
    def testTrivial: Unit = {
        AssertThrows(throw new Error())
    }
}
