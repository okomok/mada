

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual.assert
import mada.dual.free
import mada.dual.nat._


class ConversionTest extends org.scalatest.junit.JUnit3Suite {

    def testToPeano {
        import junit.framework.Assert._
        free.assertSame[peano._0, dense._0#asNatPeano]
        free.assertSame[peano._6, dense._6#asNatPeano]
        free.assertSame[peano._5, dense._2#plus[dense._3]#asNatPeano]

        type x = dense._5
         val x: x = dense._5
        type y = x#asNatPeano
         val y: y = x.asNatPeano
        mada.dual.assert(peano._5 equal y)
    }

}
