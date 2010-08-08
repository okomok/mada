

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.peano.Literal._
    import junit.framework.Assert._

    class DocTest extends org.scalatest.junit.JUnit3Suite {
        // Define 0-ary dualmethod `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            type self = not2 // `self` is the dual version of `this` reference. Manual setup is needed.
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asInstanceOfNat.equal(_2).not
            override type apply[x <: dual.Any] = x#asInstanceOfNat#equal[_2]#not
        }
        val not2 = new not2

        def testTrivial {
            // Filter a heterogeneous list.
            type xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            type fs = xs#filter[not2] // `filter` returns a view.
            dual.meta.assertSame[_3 :: _4 :: _5 :: _6 :: dual.Nil, fs#force]
            // Because of duality, a runtime test might be unneeded.
            val xs: xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            val fs: xs#filter[not2] = xs.filter(not2)
            assertEquals(_3 :: _4 :: _5 :: _6 :: dual.Nil, fs)
        }
    }
