

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class ClearTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type m1    = set.sorted[nat.naturalOrdering]#add[_4]#add[_3]#add[_1]#add[_2]#add[_5]#add[_0]
        val m1: m1 = set.sorted(nat.naturalOrdering).add(_4).add(_3).add(_1).add(_2).add(_5).add(_0)

        type n = m1#clear
        val n: n = m1.clear

        meta.assert[n#isEmpty]
        assertTrue(n.isEmpty.undual)
    }

}
