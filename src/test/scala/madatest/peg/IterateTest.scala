

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector

import mada.Peg.Compatibles._
import mada.Peg._


class IterateTest {
    def testTrivial: Unit = {
        val sb = new StringBuilder
        val w = mada.Vector.from("XXabcXXabcXXabcXXabc").nth

        val p = (-single('X')).+ until ~("X"|end)
        var k = 0
        for (Vector.Region(v, i, j) <- p.split(w)) {
            assertSame(w, v)
            sb append Vector.stringize(v(k, i))
            sb append Vector.stringize(v(i, j))
            k = j
        }

        assertEquals(w, mada.Vector.from(sb.toString))
    }
}
