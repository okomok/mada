

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package utiltest


import com.github.okomok.mada

import mada.util
import junit.framework.Assert._


class TimesTest extends junit.framework.TestCase {

    def testRepeat: Unit = {
        var i = 0
        try {
            util.repeat {
                i += 1
                if (i == 10)
                    throw new Error
            }
        } catch {
            case _: Error =>
        }
        assertEquals(10, i)
    }

    def testTimes {
        var i = 0
        util.times(5) {
            i += 1
            "wow"
        }
        assertEquals(5, i)
    }

    def testTimesParallel {
        util.times(10) {
            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            //var i @volatile = 0
            util.timesParallel(5) {
                i.incrementAndGet
                //i += 1 // wake up man.
                "wow"
            }
            assertEquals(5, i.get)
        }
    }

}
