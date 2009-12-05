

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class TakeTest {
    val ones = vector.Of(1,1,1,1)

    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).take(3).activate(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(1,2,3,99), vector.from(b))
    }

    def testTrivial: Unit = {
        val src = new IntSenders(ones, ones)
        val dst = new IntReceiver(vector.single(1).times(6))
        (src(0) merge src(1)).synchronize.take(6).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testOrder: Unit = {
        val src = new IntSenders(vector.Of(1,2,3,4,5,6))
        val dst = new IntReceiver(vector.Of(1,2,3,4))
        (src(0) merge reactive.empty).synchronize.take(4).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testNonTrivial: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(11))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).synchronize.take(11).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testFusion: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(11))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).synchronize.take(17).take(11).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testEmpty: Unit = {
        val src = new IntSenders(vector.empty[Int], vector.empty[Int])
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1)).synchronize.take(3).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testZero: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).synchronize.take(0).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testTooMany: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(20))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).synchronize.take(500).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }

    def testBound: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(20))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).synchronize.take(20).activate(dst)
        src.activate
        src.shutdown(dst.assertMe)
        ()
    }
}
