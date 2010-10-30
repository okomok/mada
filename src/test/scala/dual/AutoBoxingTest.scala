

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._

import junit.framework.Assert._


class AutoBoxingTest extends org.scalatest.junit.JUnit3Suite {

    def testList {
        val xs = 1 :: 2 :: Nil
        val x: Box[Int] = xs.head
        val i: Int = x
        assertEquals(1, i)
    }

    def testList2 {
        class Wow {
            def foo = ()
        }
        val xs = new Wow :: "boxing" :: Nil
        val x: Box[Wow] = xs.head
        xs.head.foo
    }

}
