

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

    // Define dual `Function2`.
    object Apply extends Function2 {
        override type self = Apply.type
        override  def apply[f <: Any, x <: Any](f: f, x: x): apply[f, x] = f.asFunction1.apply(x)
        override type apply[f <: Any, x <: Any]                          = f#asFunction1#apply[x]
    }

    def testTrivial {
        // Normal types are not first-class citizen in dual world.
        // `Box` turns them into dual ones.
        val y1 = 9.75 #:: 'x' #:: Nil // == Box(9.75) :: Box('x') :: Nil
        val y2 = -2.125 #:: 'X' #:: Nil

        // `Function` too is a kind of boxing, which turns a normal function into dual one.
        val z = function.Lift1((_: Double) + .5) :: function.Lift1((_: Char).isUpper) :: Nil

        // `zipBy` returns a view(unspecified type). `force` turns a view into a concrete list.
        val z1: Box[scala.Double] :: Box[scala.Boolean] :: Nil = z.zipBy(y1, Apply).force
        val Box(10.25) :: Box(false) :: Nil = z1

        val z2: Box[scala.Double] :: Box[scala.Boolean] :: Nil = z.zipBy(y2, Apply).force
        val Box(-1.625) :: Box(true) :: Nil = z2

        {
            // escape from the dual world using `undual`.
            val :: = scala.::
            val 10.25 :: false :: scala.Nil = z1.undual
            val -1.625 :: true :: scala.Nil = z2.undual
        }
        ()
    }

    def testTrivial2 {
        val y1 = 9.75 #:: 'x' #:: Nil
        val z = fnction.Lift1((_: Double) + .5) :: Box(3) :: Nil
        z.zipBy(y1, Apply) // doesn't crash for now, because this is a view...
        ()
    }

}

