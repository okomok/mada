

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class ZipTest {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3)
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(3,"4"),(99,"99")), iterative.from(out))
    }

    def testEmpty1: Unit = {
        val t = reactive.empty.of[Int]
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((99,"99")), iterative.from(out))
    }

    def testEmpty2: Unit = {
        val t = reactive.empty.of[Int]
        val u = reactive.empty.of[String]
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((99,"99")), iterative.from(out))
    }

    def testOneShorter1: Unit = {
        val t = reactive.Of(1,2)
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(99,"99")), iterative.from(out))
    }

    def testOneShorter2: Unit = {
        val t = reactive.Of(1,2,3)
        val u = reactive.Of("2","3")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(99,"99")), iterative.from(out))
    }

    def testMuchShorter1: Unit = {
        val t = reactive.Of(1,2)
        val u = reactive.Of("2","3","4","5","6")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(99,"99")), iterative.from(out))
    }

    def testMuchShorter2: Unit = {
        val t = reactive.Of(1,2,3,4,5,6)
        val u = reactive.Of("2","3")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).subscribe(reactor.make(_ => out.add((99,"99")), out.add(_)))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(99,"99")), iterative.from(out))
    }
/*
    def testParallel: Unit = {
        for (_ <- 0 to 30) {
            val src = new IntSenders(vector.Of(1,2,3,4,5,6), vector.Of(7,7,7,7))
            val dst = new IntReceiver(vector.Of(8,9,10,11))
            (src(0).zipBy(src(1))(_ + _)).subscribe(dst)
            src.subscribe
            src.shutdown(dst.assertMe)
        }
    }
*/
}
