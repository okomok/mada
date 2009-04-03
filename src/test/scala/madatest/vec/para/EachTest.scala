

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.para


import mada.Vector._

import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class EachTest {
    def testTrivial: Unit = {
        val ex = Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14)
        mada.Vector.from(ex).parallel.each(print(_))
        mada.Vector.from(ex).parallel(6).each(print(_))
        mada.Vector.from(ex).parallel(100).each(print(_))
    }

    def print(i: Int) = {
        //println(i)
        ()
    }
}
