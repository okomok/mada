

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vectors

import mada.Peg.Compatibles._
import mada.Peg._


class IterateTest {
    def testTrivial: Unit = {
        val sb = new StringBuilder
        val w = mada.Vectors.from("XXabcXXabcXXabcXXabc")

        val p = -single('X') +? ("X"|end)
        var k = 0
        for (Vectors.Region(v, i, j) <- p.tokenize(w)) {
            assertSame(w, v)
            sb append Vectors.stringize(v(k, i))
            sb append Vectors.stringize(v(i, j))
            k = j
        }

        assertEquals(w, mada.Vectors.from(sb.toString))
    }
}
