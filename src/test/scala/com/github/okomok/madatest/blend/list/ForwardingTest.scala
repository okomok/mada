

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package forwardingtest


import com.github.okomok.mada

import mada.blend._
import mada.meta._


class ForwardingTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    //def foo[l <: List](_l: l) = _l.drop[_3N]

    case class CallDrop3[l <: List](_l: l) {
        def apply(implicit _drop: list.Drop[_l.self, _3N]) = _l.drop[_3N]
    }

    def testTrivial: Unit = {
        val l = 3 :: 'c' :: 3.0 :: "wow" :: Nil
        val r = CallDrop3(l).apply
        assertEquals("wow"::Nil, r)
    }
}
