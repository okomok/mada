

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package utiltest


import com.github.okomok.mada

import mada.util
import junit.framework.Assert._


class FutureTest extends junit.framework.TestCase {
    class Wrap(val i: Int) extends RuntimeException

    def testTrivial: Unit = {
        val a = util.future(new Wrap(11))
        assertEquals(11, a().i)
        assertSame(a(), a())
    }

    def testThrow: Unit = {
        val a = util.future{throw new Wrap(11); 12}
        var thrown = false
        try {
            a()
        } catch {
            case e: Wrap => thrown = true
        }
        assertEquals(true, thrown)
    }
}

class FutureDaemonTest extends junit.framework.TestCase {
    def testDaemon: Unit = {
        // In fact, junit always makes children daemon-ize,
        // so that I don't know how to test....
        val a = util.future(while(true){})
        ()
    }
}
