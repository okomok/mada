

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.para


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class EachTest {
    def testTrivial: Unit = {
        val ex = Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14)
        mada.sequence.vector.from(ex).parallel.each(print(_))
        mada.sequence.vector.from(ex).parallel(6).each(print(_))
        mada.sequence.vector.from(ex).parallel(100).each(print(_))
    }

    def print(i: Int) = {
        //println(i)
        ()
    }
}
