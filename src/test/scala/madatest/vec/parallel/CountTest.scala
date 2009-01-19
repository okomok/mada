

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vector._
import mada.Vector.Compatibles._
import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class CountTest {
    def testTrivial: Unit = {
        val v = madaVector("a813a91ng8a89a8")
        assertEquals(4L, v.parallel.count(_ == 'a'))
        assertEquals(4L, v.parallel(1000).count(_ == 'a'))
        assertEquals(4L, v.parallel(6).count(_ == 'a'))

        assertEquals(0L, v.parallel.count(_ == 'z'))
        assertEquals(0L, v.parallel(1000).count(_ == 'z'))
        assertEquals(0L, v.parallel(6).count(_ == 'z'))
    }
}
