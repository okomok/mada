

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.rng._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class RandomAccessSeqTest {
    def testTrivial() = {
        detail.TestRandomAccessReadWrite(example1, from(example1.asInstanceOf[RandomAccessSeq[Int]]).eval)
    }
}
