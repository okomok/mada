

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package peanotest


import com.github.okomok.mada

import mada.dual.assert
import mada.dual.meta
import mada.dual.nat._


class ConversionTest extends org.scalatest.junit.JUnit3Suite {

    def testToDense {
        import junit.framework.Assert._
        meta.assertSame[dense._0, peano._0#asNatDense]
        meta.assertSame[dense._1, peano._1#asNatDense]
        meta.assertSame[dense._6, peano._6#asNatDense]
        meta.assertSame[dense._5, peano._2#plus[peano._3]#asNatDense]

        type x = peano._5
         val x: x = peano._5
        type y = x#asNatDense
         val y: y = x.asNatDense
        mada.dual.assert(dense._5 equal y)
    }

}
