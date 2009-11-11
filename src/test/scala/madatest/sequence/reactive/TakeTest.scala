

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence.reactive
import junit.framework.Assert._


import mada.sequence.vector


class TakeTest {
    val ones = vector.Of(1,1,1,1)

    def testTrivial: Unit = {
        val src = new IntSenders(ones, ones)
        val dst = new IntReceiver(vector.single(1).times(6))
        (src(0) merge src(1)).take(6).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testNonTrivial: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(11))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).take(11).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testFusion: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(11))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).take(17).take(11).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testOrder: Unit = {
        val src = new IntSenders(vector.Of(1,2,3,4,5,6))
        val dst = new IntReceiver(vector.Of(1,2,3,4))
        (src(0) merge reactive.empty).take(4).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testEmpty: Unit = {
        val src = new IntSenders(vector.empty[Int], vector.empty[Int])
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1)).take(3).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testZero: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.empty[Int])
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).take(0).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testTooMany: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(20))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).take(500).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testBound: Unit = {
        val src = new IntSenders(ones, ones, ones, ones, ones)
        val dst = new IntReceiver(vector.single(1).times(20))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).take(20).subscribe(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

}
