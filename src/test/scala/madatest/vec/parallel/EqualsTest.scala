

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vector._
import mada.Vector.Compatibles._
import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class EqualsTest {
    def testTrivial: Unit = {
        assertTrue(example1.parallel(1000) == madaVector(example1))
        assertTrue(example1.parallel(6) equalsTo example1)
        assertTrue(example1.parallel == madaVector(example1))
        assertFalse(example1.parallel == madaVector(example2))
    }
}
