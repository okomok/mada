

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class FoldRightTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class ToString extends Function2[Any, Any, Any] {
        override type apply[v1 <: Any, v2 <: Any] = String
        override def apply[v1 <: Any, v2 <: Any](_v1: v1, _v2: v2) = _v1.toString + _v2
    }

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l1 = Int :: String :: java.lang.Integer :: Nil
        val l1: l1 = 3 :: "hello" :: i :: Nil

        val r: l1#foldRight_Any[String, ToString] = l1.foldRight_Any("", new ToString)

        assertEquals("3hello10", r)
    }

}
