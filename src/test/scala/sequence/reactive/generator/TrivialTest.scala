

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest;
package sequencetest; package reactivetest; package generatortest


import com.github.okomok.mada
import mada.sequence.iterative
import mada.sequence.reactive.generator
import junit.framework.Assert._


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    def testEmpty: Unit = {
        val r = generator.empty
        assertTrue(r.isEmpty)
        val out = new java.util.ArrayList[Int]
        r.sequence.foreach(out.add(_))
        r.generate
        r.generate
        assertTrue(iterative.from(out).isEmpty)
        r.generate
    }

    def testUnfoldRight: Unit = {
        val r = generator.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        val out = new java.util.ArrayList[Int]
        r.sequence.foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), iterative.from(out))
    }

    def testIterate: Unit = {
        val br = new scala.util.control.Breaks
        import br._
        val r = generator.iterate(10){ b => b-1 }
        val out = new java.util.ArrayList[Int]
        r.sequence.take(10).foreach(out.add(_))
        r.generateN(10)
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), iterative.from(out))
    }

    def testRepeat: Unit = {
        val br = new scala.util.control.Breaks
        import br._
        val r = generator.repeat(10)
        val out = new java.util.ArrayList[Int]
        r.sequence.take(5).foreach(out.add(_))
        r.generateN(15)
        assertEquals(iterative.Of(10,10,10,10,10), iterative.from(out))
    }

/* rejected
    def testThen: Unit = {
        val r = reactive.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        val out = new java.util.ArrayList[Int]
        r.then(out.add(99)).then(out.add(98)).foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1,99,98), iterative.from(out))
    }
*/
}
