

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._
import junit.framework.Assert._


class TransformerTest {

    def testTrivial {
        val ff = list.transformer((x: Any, y: Any) => x.toString + y.toString)
        val hh = ff((x: Char) => x.toString, ff((x: Int) => x.toString, (Nil: Nil) => ""))
        val vv = 'a' :: 3 :: Nil
        val kk: String = hh(vv)
        assertEquals("a3", kk)
    }

}
