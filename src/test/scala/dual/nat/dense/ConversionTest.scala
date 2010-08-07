

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual.assert
import mada.dual.meta
import mada.dual.nat._


class ConversionTest extends org.scalatest.junit.JUnit3Suite {

    def testToPeano {
        import junit.framework.Assert._
        meta.assertSame[peano._0, dense._0#toPeano]
        meta.assertSame[peano._6, dense._6#toPeano]
        meta.assertSame[peano._5, dense._2#plus[dense._3]#toPeano]

        type x = dense._5
         val x: x = dense._5
        type y = x#toPeano
         val y: y = x.toPeano
        mada.dual.assert(peano._5 equal y)
    }

}
