

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vectors._

import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class FoldTest {
    def testTrivial: Unit = {
        val v = mada.Vectors.from(example1)
        assertEquals(v.foldLeft(3)(_ + _), v.parallel.fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallel(6).fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallel(1000).fold(3)(_ + _))
    }
}
