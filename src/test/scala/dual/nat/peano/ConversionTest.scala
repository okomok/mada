

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package peanotest


import com.github.okomok.mada

import mada.dual.assert
import mada.dual.meta
import mada.dual.nat._


class ConversionTest extends junit.framework.TestCase {

    def testToDense {
        import junit.framework.Assert._
        meta.assertSame[dense._0, peano._0#toDense]
        meta.assertSame[dense._1, peano._1#toDense]
        meta.assertSame[dense._6, peano._6#toDense]
        meta.assertSame[dense._5, peano._2# +[peano._3]#toDense]

        type x = peano._5
         val x: x = peano._5
        type y = x#toDense
         val y: y = x.toDense
        assert(dense._5 === y)
    }

}
