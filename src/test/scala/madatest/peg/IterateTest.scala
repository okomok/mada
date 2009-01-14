

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class IterateTest {
    def testTrivial: Unit = {
        val sb = new StringBuilder
        val w = madaVector("XXabcXXabcXXabcXXabc")

        val p = -single('X') +? ("X"|end)
        var k = 0L
        for ((v, i, j) <- p.tokenize(w)) {
            assertSame(w, v)
            sb append Vector.toString(v.window(k, i))
            sb append Vector.toString(v.window(i, j))
            k = j
        }

        assertEquals(w, madaVector(sb.toString))
    }
}
