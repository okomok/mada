

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class RemoveTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type o = nat.ord
        val o: o = nat.ord

        type m = set.sorted[o]#add[_3]#add[_5]#add[_1]
        val m: m = set.sorted(o).add(_3).add(_5).add(_1)

        type rm = m#remove[_5]
        val rm: rm = m.remove(_5)
        meta.assertSame[nat.dense._2, rm#size]
        meta.assertSame[`false`, rm#contains[_5]]
        ()
    }

}
