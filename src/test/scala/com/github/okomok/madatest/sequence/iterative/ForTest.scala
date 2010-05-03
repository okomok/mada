

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ForTest {
    def testLazy: Unit = {
        var limit = 2
        val it = for (i <- iterative.Of(0, 1, 2, 3) if i <= limit; j <- vector.range(0,limit)) yield { limit = 4; (i, j) }

        // This is probably guaranteed: imagine how function-literal is generated.
        it.flatMap(i => vector.range(0, 9))
        val k: Int => Iterative[Int] = i => vector.range(0, 9)

        val a = iterative.Of((0,0), (0,1), (1,0), (1,1), (1,2), (1,3), (2,0), (2,1), (2,2), (2,3), (3,0), (3,1), (3,2), (3,3))
        assertEquals(a, it)
//        assertEquals(a, it) // error, because of the side-effect.
    }

/* Never ends!
    def testBreak: Unit = {
        var break = false
        for (i <- iterative.Of(0, 1, 2).cycle if !break) {
            if (i == 100) {
                break = true
            }
        }
    }
*/
}
