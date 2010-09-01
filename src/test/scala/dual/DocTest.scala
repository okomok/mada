

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


    import com.github.okomok.mada.dual
    import dual.{map, Nat, Box}
    import dual.nat.dense.Literal._

    // A dual version of abstract-factory pattern
    class DocTest extends org.scalatest.junit.JUnit3Suite {
        // Notice there is no common super trait.
        class WinButton {
            def paint { println("I'm a WinButton") }
        }
        class OSXButton {
            def paint { println("I'm a OSXButton") }
        }

        object WinFactory {
            def createButton = new WinButton
        }
        object OSXFactory {
            def createButton = new OSXButton
        }

        // Needs explicit boxing to make a dual object from a non-dual one.
        val factoryMap = map.sorted1(_0, Box(WinFactory)).put(_1, Box(OSXFactory))

        def createFactory[n <: Nat](n: n) = {
            val option = factoryMap.get(n)
            option.get.undual
        }

        def testTrivial {
            // Concrete types are preserved.
            val factory = createFactory(_0)
            val button = factory.createButton
            button.paint // I'm a WinButton
        }
    }



/*
    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.dense.Literal._

    class DocTest extends org.scalatest.junit.JUnit3Suite {
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
            dual.free.assert(xs.filter(not2).equal(ys)) // checked in compile-time.
        }
    }
    */

