

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest; package doctest


    import com.github.okomok.mada
    import mada.sequence.reactive
    import junit.framework.Assert._

    class VarTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            // `Var` is a mutable one-element sequence.
            val a = new reactive.Var(1)
            val b = new reactive.Var(2)

            var z = 0
            for (x <- a; y <- b) {
                z = x + y
            }

            assertEquals(3, z)
            a := 7
            assertEquals(9, z)
            b := 35
            assertEquals(42, z)
        }
    }

