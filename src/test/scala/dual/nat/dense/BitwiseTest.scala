

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual.meta
import mada.dual.nat.dense._
import junit.framework.Assert._


class BitwiseTest extends org.scalatest.junit.JUnit3Suite {

    def testAnd {
        meta.assertSame[_0, _0# &[_0]]
        meta.assertSame[_1, _1# &[_1]]
        meta.assertSame[_0, _2# &[_5]]
        meta.assertSame[_0, _8# &[_4]]
        meta.assertSame[_5, _7# &[_5]]
        meta.assertSame[_0, _5# &[_0]]
        meta.assertSame[_3, _7# &[_3]]
        meta.assertSame[_2, _2# &[_7]]

        assertEquals(0 & 0, (_0 & _0).undual)
        assertEquals(1 & 1, (_1 & _1).undual)
        assertEquals(2 & 5, (_2 & _5).undual)
        assertEquals(8 & 4, (_8 & _4).undual)
        assertEquals(7 & 5, (_7 & _5).undual)
        assertEquals(5 & 0, (_5 & _0).undual)
        assertEquals(7 & 3, (_7 & _3).undual)
        assertEquals(2 & 7, (_2 & _7).undual)
    }

    def testOr {
        meta.assertSame[_0, _0# |[_0]]
        meta.assertSame[_1, _1# |[_1]]
        meta.assertSame[_7, _2# |[_5]]
        meta.assertSame[_12, _8# |[_4]]
        meta.assertSame[_7, _7# |[_5]]
        meta.assertSame[_5, _5# |[_0]]
        meta.assertSame[_7, _7# |[_3]]
        meta.assertSame[_7, _2# |[_7]]

        assertEquals(0 | 0, (_0 | _0).undual)
        assertEquals(1 | 1, (_1 | _1).undual)
        assertEquals(2 | 5, (_2 | _5).undual)
        assertEquals(8 | 4, (_8 | _4).undual)
        assertEquals(7 | 5, (_7 | _5).undual)
        assertEquals(5 | 0, (_5 | _0).undual)
        assertEquals(7 | 3, (_7 | _3).undual)
        assertEquals(2 | 7, (_2 | _7).undual)
    }
}
