

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest


// See: http://apocalisp.wordpress.com/2010/10/15/type-level-programming-in-scala-part-6e-hlist%C2%A0apply/


import com.github.okomok.mada
import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class LiftTest extends org.scalatest.junit.JUnit3Suite {

    object Apply extends Function2 {
        override type self = Apply.type
        override  def apply[f <: Any, x <: Any](f: f, x: x): apply[f, x] = f.asFunction1.apply(x)
        override type apply[f <: Any, x <: Any]                          = f#asFunction1#apply[x]
    }

    def testTrivial {
        val y1 = Box(9.75) :: Box('x') :: Nil
        val y2 = Box(-2.125) :: Box('X') :: Nil

        val z = Lift1((_: Double) + .5) :: Lift1((_: Char).isUpper) :: Nil

        val z1: Box[scala.Double] :: Box[scala.Boolean] :: Nil = z.zipBy(y1, Apply).force
        val 10.25 :: false :: scala.Nil = z1.undual

        val z2: Box[scala.Double] :: Box[scala.Boolean] :: Nil = z.zipBy(y2, Apply).force
        val -1.625 :: true :: scala.Nil = z2.undual
        ()
    }
}

