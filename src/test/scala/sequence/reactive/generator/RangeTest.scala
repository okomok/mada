

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest;
package sequencetest; package reactivetest; package generatortest


import com.github.okomok.mada
import mada.sequence.vector
import mada.sequence.reactive.Generator
import junit.framework.Assert._


class RangeTest extends org.scalatest.junit.JUnit3Suite {

    def testEmpty: Unit = {
        val r = Generator.from(vector.range(1,1))
        val out = new java.util.ArrayList[Int]
        assertTrue(r.isEmpty)
        r.sequence.foreach(out.add(_))
        r.generateAll
        assertTrue(vector.from(out).isEmpty)

        // no effects
        r.generate
        r.generate
    }

    def testTrivial: Unit = {
        val r = Generator.from(vector.range(1,6))
        val out = new java.util.ArrayList[Int]
        r.sequence.foreach(out.add(_))
        r.generate
        r.generate
        r.generate
        assertEquals(vector.Of(1,2,3), vector.from(out))
        assertFalse(r.isEmpty)
    }

/* rejected
    def testEmptyWithEnd: Unit = {
        val out = new java.util.ArrayList[Int]
        val r = generator.range(1,1,out.add(99))
        r.foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(99), iterative.from(out))
    }
    def testEndWith: Unit = {
        val r = generator.range(1,6,out.add(99))
        val out = new java.util.ArrayList[Int]
        r.then(out.add(99)).foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(1,2,3,4,5,99), iterative.from(out))
    }
*/
}
