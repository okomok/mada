

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package utiltest


import com.github.okomok.mada

import mada.dual.nat.Peano.Literal._
import mada.util._
import junit.framework.Assert._


class ConstraintTest extends junit.framework.TestCase {

    trait Foo[+A]

    trait B
    trait A extends B

    def testIsSameTrivial {
        assertTrue(isSame[Double, Double])
        assertTrue(isSame[Int, Int])
        assertFalse(isSame[Int, String])
        assertFalse(isSame[String, Float])
        assertTrue(isSame[_1# +[_2], _3])
        assertFalse(isSame[_2# +[_2], _5])
        assertFalse(isSame[A, B])
        assertFalse(isSame[B, A])
    }

    def testIsSameNoErasure {
        assertTrue(isSame[Foo[Int], Foo[Int]])
        assertFalse(isSame[Foo[Char], Foo[String]])
    }


    def testConformsTrivial {
        assertTrue(conforms[A, AnyRef])
        assertTrue(conforms[A, B])
        assertFalse(conforms[B, A])
        assertFalse(conforms[Int, A])
        assertFalse(conforms[A, Char])
        assertTrue(conforms[Int, AnyVal])
    }

    def testConformsSame {
        assertTrue(conforms[Double, Double])
        assertTrue(conforms[Int, Int])
        assertFalse(conforms[Int, String])
        assertFalse(conforms[String, Float])
        assertTrue(conforms[_1# +[_2], _3])
        assertFalse(conforms[_2# +[_2], _5])
    }

    def testConformsNoErasure {
        assertTrue(conforms[Foo[A], Foo[B]])
        assertFalse(conforms[Foo[Char], Foo[String]])
    }


    def testCompatibleTrivial {
        assertTrue(compatible[A, AnyRef])
        assertTrue(compatible[A, B])
        assertFalse(compatible[B, A])
        assertFalse(compatible[Int, A])
        assertFalse(compatible[A, Char])
        assertTrue(compatible[Int, AnyVal])
    }

    def testCompatibleSame {
        assertTrue(compatible[Double, Double])
        assertTrue(compatible[Int, Int])
        assertFalse(compatible[Int, String])
        assertFalse(compatible[String, Float])
        assertTrue(compatible[_1# +[_2], _3])
        assertFalse(compatible[_2# +[_2], _5])
    }

    def testCompatibleNoErasure {
        assertTrue(compatible[Foo[A], Foo[B]])
        assertFalse(compatible[Foo[Char], Foo[String]])
    }

    def testCompatibleCompatible {
        assertTrue(compatible[Long, Float])
        assertFalse(compatible[Float, Long])
        assertTrue(compatible[Int, scala.runtime.RichInt])
    }


    def testHasImplicit {
        implicit val x = 3
        assertTrue(hasImplicit[Int])
        assertTrue(hasImplicit[Int =:= Int])
    }

    def testHasImplicitNegative {
        assertFalse(hasImplicit[Int])
        assertFalse(hasImplicit[Char =:= Int])
    }

}
