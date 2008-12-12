

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.Generate._
import junit.framework.Assert._


class GenerateTest {
    def testTrivial: Unit = {
        var i = 6
        val x = generate({ (x: Unit) => i -= 1; if (i == 0) None else Some(i) })
        detail.TestSinglePassReadOnly(Array(5,4,3,2,1), x.eval)
    }

    def testEmpty: Unit = {
        val x = generate({ (x: Unit) => None })
        detail.TestEmpty(x.eval)
    }
}
