

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest


import com.github.okomok.mada
import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._

/*
class BoxTest extends org.scalatest.junit.JUnit3Suite {

    class Apply extends Function2 {
        override type self = Apply
        override  def apply[fx <: Any](fx: fx): apply[xy] = Box(xy.asProduct2._1.undual.asInstanceOf[Function1[_]].apply(xy.asProduct2._2))
        override type apply[fx <: Any] =
    }
    val Apply = new Apply

    def testTrivial {
        val y1 = Box(9.75) :: Box('x') :: Nil
        val y2 = Box(-2.125) :: Box('X') :: Nil

        val z = Box((_: Double) + .5) :: Box((_: Char).isUpper) :: Nil




        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val u: xs#forall[Gt3] = xs.forall(Gt3())
        free.assertSame[`true`, xs#forall[Gt3]]
        assertEquals(`true`, u)
    }
}
*/
