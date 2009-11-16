

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class FlattenTest {

    def testFlatMap: Unit = {
        val tr = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        tr.flatMap{e => reactive.fromIterative(vector.range(0, e))}.subscribe(reactor.make(out.add(99), out.add(_)))
        assertEquals(iterative.Of(0,0,1,0,1,2,0,1,2,3,0,1,2,3,4, 99), iterative.from(out))
    }

}
