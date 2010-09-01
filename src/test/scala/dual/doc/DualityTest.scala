

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package doctest

    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.dense.Literal._

    class DualityTest extends org.scalatest.junit.JUnit3Suite {
        // Define 0-ary dualmethod `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            type self = not2 // `self` is the dual version of `this` reference. Manual setup is needed.
            // Again no free-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asNat.equal(_2).not
            override type apply[x <: dual.Any]                 = x#asNat#equal[_2]#not
        }
        val not2 = new not2

        def testTrivial {
            // Filter a heterogeneous list.
            val xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            val ys = _3 :: _4 :: _5 :: _6 :: dual.Nil
            val zs = xs.filter(not2).equal(ys)
            dual.free.assert(zs) // checked in compile-time thanks to the duality.
        }
    }
