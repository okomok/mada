

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._
import mada.{Vector, vector}

import mada.peg.compatibles._
import mada.peg._


class IterateTest {
    def testTrivial: Unit = {
        val sb = new StringBuilder
        val w = mada.vector.from("XXabcXXabcXXabcXXabc").nth

        val p = (-single('X')).+ until ~("X"|end)
        var k = 0
        for (vector.Region(v, i, j) <- p.tokenize(w)) {
            assertSame(w, v)
            sb append vector.stringize(v(k, i))
            sb append vector.stringize(v(i, j))
            k = j
        }

        assertEquals(w, mada.vector.from(sb.toString))
    }
}
