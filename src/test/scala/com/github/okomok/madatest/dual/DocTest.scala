

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.peano.Literal._
    import junit.framework.Assert._

    class DocTest extends junit.framework.TestCase {
        // Define dualvalue `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            // `self` is the dual version of `this` reference. Manual setup is needed.
            override  def self = this
            override type self = not2
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asInstanceOfNatPeano !== _2
            override type apply[x <: dual.Any] = x#asInstanceOfNatPeano# !==[_2]
        }
        val not2 = new not2

        def testTrivial {
            // Filter heterogeneous list.
            type xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            dual.meta.assertSame[_3 :: _4 :: _5 :: _6 :: dual.Nil, xs#filter[not2]]
            // Because of duality, a runtime test might be unneeded.
            val xs: xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            val fs: xs#filter[not2] = xs.filter(not2)
            assertEquals(_3 :: _4 :: _5 :: _6 :: dual.Nil, fs)
        }
    }



    class DocOldTest extends junit.framework.TestCase {
        class slice {
            // List and Nat are metatypes. xs, n, and m are dualvalues. take and drop are dualmethods.
             def apply[xs <: dual.List, n <: dual.nat.Peano, m <: dual.nat.Peano](xs: xs, n: n, m: m): apply[xs, n, m] = xs.take(m).drop(n)
            type apply[xs <: dual.List, n <: dual.nat.Peano, m <: dual.nat.Peano] = xs#take[m]#drop[n]
        }
        val slice = new slice

        def testSlice {
            type xs = _5 :: _6 :: _7 :: _8 :: dual.Nil
            val xs  = _5 :: _6 :: _7 :: _8 :: dual.Nil
            val ys: slice#apply[xs, _1, _3] = slice(xs, _1, _3)
            val zs: _6 :: _7 :: dual.Nil = ys
            assertEquals(_6 :: _7 :: dual.Nil, zs)
        }
    }

