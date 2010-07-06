

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.Literal._
    import junit.framework.Assert._

    class DocTest extends junit.framework.TestCase {
        // Define dualvalue `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            // `self` is the dual version of `this` reference. Manual setup is needed.
            override  def self = this
            override type self = not2
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asInstanceOfNat !== _2N
            override type apply[x <: dual.Any] = x#asInstanceOfNat# !==[_2N]
        }
        val not2 = new not2

        def testTrivial {
            // Filter heterogeneous list.
            type xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: dual.Nil
            dual.meta.assertSame[_3N :: _4N :: _5N :: _6N :: dual.Nil, xs#filter[not2]]
            // Because of duality, a runtime test might be unneeded.
            val xs: xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: dual.Nil
            val fs: xs#filter[not2] = xs.filter(not2)
            assertEquals(_3N :: _4N :: _5N :: _6N :: dual.Nil, fs)
        }
    }



    class DocOldTest extends junit.framework.TestCase {
        class slice {
            // List and Nat are metatypes. xs, n, and m are dualvalues. take and drop are dualmethods.
             def apply[xs <: dual.List, n <: dual.Nat, m <: dual.Nat](xs: xs, n: n, m: m): apply[xs, n, m] = xs.take(m).drop(n)
            type apply[xs <: dual.List, n <: dual.Nat, m <: dual.Nat] = xs#take[m]#drop[n]
        }
        val slice = new slice

        def testSlice {
            type xs = _5N :: _6N :: _7N :: _8N :: dual.Nil
            val xs  = _5N :: _6N :: _7N :: _8N :: dual.Nil
            val ys: slice#apply[xs, _1N, _3N] = slice(xs, _1N, _3N)
            val zs: _6N :: _7N :: dual.Nil = ys
            assertEquals(_6N :: _7N :: dual.Nil, zs)
        }
    }

