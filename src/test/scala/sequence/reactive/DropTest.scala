

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._


import mada.sequence.vector


class DropTest extends junit.framework.TestCase {
    val ones = vector.Of(1,1,1,1)

    def testTrivial: Unit = {
        val src = new IntSenders(ones, ones)
        val dst = new IntReceiver(vector.single(1).times(4*2-2))
        (src(0) merge src(1)).drop(2).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testNonTrivial: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(4*5-9))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).drop(9).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testFusion: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(4*5-9))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).drop(2).drop(7).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testOrder: Unit = {
        val src = new IntSenders(vector.Of(1,2,3,4,5,6))
        val dst = new IntReceiver(vector.Of(5,6))
        (src(0) merge reactive.empty).drop(4).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testEmpty: Unit = {
        val src = new IntSenders(vector.empty[Int], vector.empty[Int])
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1)).drop(3).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testZero: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(4*5))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).drop(0).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testTooMany: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).drop(500).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }
}
