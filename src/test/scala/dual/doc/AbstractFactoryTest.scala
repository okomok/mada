

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package doctest

    import com.github.okomok.mada.dual
    import dual.{map, Nat, Box}
    import dual.nat.dense.Literal._
    import junit.framework.Assert.assertEquals

    class AbstractFactoryTest extends org.scalatest.junit.JUnit3Suite {
        // Notice there is no common super trait.
        class WinButton {
            def paint = "I'm a WinButton"
        }
        class OSXButton {
            def paint = "I'm a OSXButton"
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
            assertEquals("I'm a WinButton", button.paint)
        }
    }
