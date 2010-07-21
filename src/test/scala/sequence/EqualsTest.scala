

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class EqualsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val I1: Iterative[Int] = Iterative(27,10,14,7,4,1,2)
        val I2: Iterative[Int] = Iterative(27,10,14,7,4,1,2)
        val V1: Vector[Int] = Vector(27,10,14,7,4,1,2)
        val V2: Vector[Int] = Vector(27,10,14,7,4,1,2)
        val L1: List[Int] = List(27,10,14,7,4,1,2)
        val L2: List[Int] = List(27,10,14,7,4,1,2)

        assertEquals(I1, I2)
        assertEquals(I1, V2)
        assertEquals(I1, L2)

        assertEquals(V1, I2)
        assertEquals(V1, V2)
        assertEquals(V1, L2)

        assertEquals(L1, I2)
        assertEquals(L1, V2)
        assertEquals(L1, L2)
    }

    def testNot: Unit = {
        val I1: Iterative[Int] = Iterative(27,10,14,7,4,1,2)
        val I2: Iterative[Int] = Iterative(27,10,14,7,40,1,2)
        val V1: Vector[Int] = Vector(27,10,14,7,4,1,2)
        val V2: Vector[Int] = Vector(27,10,14,7,40,1,2)
        val L1: List[Int] = List(27,10,14,7,4,1,2)
        val L2: List[Int] = List(27,10,14,7,40,1,2)

        AssertNotEquals(I1, I2)
        AssertNotEquals(I1, V2)
        AssertNotEquals(I1, L2)

        AssertNotEquals(V1, I2)
        AssertNotEquals(V1, V2)
        AssertNotEquals(V1, L2)

        AssertNotEquals(L1, I2)
        AssertNotEquals(L1, V2)
        AssertNotEquals(L1, L2)
    }

    def testNotSize: Unit = {
        val I1: Iterative[Int] = Iterative(27,10,14,7,4,1,2)
        val I2: Iterative[Int] = Iterative(27,10,14,7,4,1,2,99)
        val V1: Vector[Int] = Vector(27,10,14,7,4,1,2)
        val V2: Vector[Int] = Vector(27,10,14,7,4,1,2,99)
        val L1: List[Int] = List(27,10,14,7,4,1,2)
        val L2: List[Int] = List(27,10,14,7,4,1,2,99)

        AssertNotEquals(I1, I2)
        AssertNotEquals(I1, V2)
        AssertNotEquals(I1, L2)

        AssertNotEquals(V1, I2)
        AssertNotEquals(V1, V2)
        AssertNotEquals(V1, L2)

        AssertNotEquals(L1, I2)
        AssertNotEquals(L1, V2)
        AssertNotEquals(L1, L2)
    }

    def testEmpty: Unit = {
        val I1: Iterative[Int] = iterative.empty.of[Int]
        val I2: Iterative[Int] = iterative.empty.of[Int]
        val V1: Vector[Int] = vector.empty[Int]
        val V2: Vector[Int] = vector.empty[Int]
        val L1: List[Int] = list.empty.of[Int]
        val L2: List[Int] = list.empty.of[Int]

        assertEquals(I1, I2)
        assertEquals(I1, V2)
        assertEquals(I1, L2)

        assertEquals(V1, I2)
        assertEquals(V1, V2)
        assertEquals(V1, L2)

        assertEquals(L1, I2)
        assertEquals(L1, V2)
        assertEquals(L1, L2)
    }
}
