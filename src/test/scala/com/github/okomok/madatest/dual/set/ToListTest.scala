

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class ToListTest extends junit.framework.TestCase {

    def testTrivial {
        type m   = set.sorted[nat.ord]#add[_4]#add[_3]#add[_1]#add[_2]#add[_5]#add[_0]
        val m: m = set.sorted(nat.ord).add(_4).add(_3).add(_1).add(_2).add(_5).add(_0)

        type l   = _0 :: _1 :: _2 :: _3 :: _4 :: _5 :: Nil
        val l: l = _0 :: _1 :: _2 :: _3 :: _4 :: _5 :: Nil
        meta.assertSame[l, m#toList]
        assertEquals(l, m.toList)
   }

}
