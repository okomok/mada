

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._

import vector.range


class MergeTest {
    def testTrivial: Unit = {
        val src = new IntSenders(range(0, 5), range(5, 12))
        val dst = new IntReceiver(range(0, 12))
        (src(0) merge src(1)).start(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }

    def testNonTrivial: Unit = {
        val src = new IntSenders(range(0, 5), range(5, 12), range(12, 13), range(13, 20), range(20, 26))
        val dst = new IntReceiver(range(0, 26))
        (src(0) merge src(1) merge src(2) merge src(3) merge src(4)).start(dst)
        src.start
        src.shutdown(dst.assertMe)
        ()
    }
}
