

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.{assert => dassert}
import nat.dense.Literal._
import junit.framework.Assert._


class RepeatTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type rs = list.repeat[_3]
        val rs: rs = list.repeat(_3)
        meta.assert[rs#take[_4]#equivTo[_3 :: _3 :: _3 :: _3 :: Nil, nat.naturalOrdering]]
        assertEquals(_3 :: _3 :: _3 :: _3 :: Nil, rs.take(_4))
    }

}
