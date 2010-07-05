

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest

/*
    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.Literal._
    import junit.framework.Assert._

    class DocTest extends junit.framework.TestCase {
        class slice {
            // List and Nat are metatypes. xs, n, and m are dualvalues. take and drop are dualmethods.
             def apply[xs <: dual.List, n <: dual.Nat, m <: dual.Nat](xs: xs, n: n, m: m): apply[xs, n, m] = xs.take(m).drop(n)
            type apply[xs <: dual.List, n <: dual.Nat, m <: dual.Nat] = xs#take[m]#drop[n]
        }
        val slice = new slice

        def testSlice: Unit = {
            type xs = _5N :: _6N :: _7N :: _8N :: dual.Nil
            val xs = _5N :: _6N :: _7N :: _8N :: dual.Nil
            val ys: slice#apply[xs, _1N, _3N] = slice(xs, _1N, _3N)
            assertEquals(3.14f :: "dual" :: dual.Nil, ys)
        }
    }
*/
