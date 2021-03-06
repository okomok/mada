

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class ContainsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from("abc1d3ef5g4qu67")

        assertTrue(v.parallel.contains('a'))
        assertTrue(v.parallel.contains('e'))
        assertTrue(v.parallel.contains('g'))
        assertFalse(v.parallel.contains('z'))

        assertTrue(v.parallelBy(1000).contains('a'))
        assertTrue(v.parallelBy(1000).contains('e'))
        assertTrue(v.parallelBy(1000).contains('g'))
        assertFalse(v.parallelBy(1000).contains('z'))

        assertTrue(v.parallelBy(6).contains('a'))
        assertTrue(v.parallelBy(6).contains('e'))
        assertTrue(v.parallelBy(6).contains('g'))
        assertFalse(v.parallelBy(6).contains('z'))

        ()
    }
}


class ContainsNoThreadsTezt extends Benchmark {
    override def run = {
        val a = longSample1.contains(405)
        ()
    }
}

class ContainsParallelTezt extends Benchmark {
    override def run = {
        val a = longSample1.parallel.contains(405)
        ()
    }
}
