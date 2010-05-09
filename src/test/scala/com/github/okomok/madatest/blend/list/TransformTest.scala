

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import junit.framework.Assert._


class TransformTest extends junit.framework.TestCase {

    def testTrivial {
        val ff = list.transform((x: Any, y: Any) => x.toString + y.toString)
        val hh = ff((x: Char) => x.toString + "C", ff((x: Int) => x.toString, (Nil: Nil) => ""))
        val vv = 'a' :: 3 :: Nil
        val kk: String = hh(vv)
        assertEquals("aC3", kk)
    }

    def testListStyle {
        val ff = list.transform((x: Any, y: Any) => x.toString + y.toString)
        val hh = { (x: String) => x.toString } :: { (x: Char) => x.toString } :: ff((x: Int) => x.toString, (Nil: Nil) => "") // ff is constant while chaining.
        val vv = "wow" :: 'a' :: 3 :: Nil
        val kk: String = hh(vv)
        assertEquals("wowa3", kk)
    }

}
