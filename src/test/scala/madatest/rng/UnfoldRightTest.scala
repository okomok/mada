

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.UnfoldRight._
import junit.framework.Assert._


/*
unfoldr      :: (b -> Maybe (a, b)) -> b -> [a]
unfoldr f b  =
  case f b of
   Just (a,new_b) -> a : unfoldr f new_b
   Nothing        -> []
*/


class UnfoldRightTest {
    def testTrivial: Unit = {
        val x = unfoldRight(5, { (b: Int) => if (b == 0) None else Some(b, b-1) })
        detail.TestSinglePassReadOnly(Array(5,4,3,2,1), x.eval)
    }

    def testEmpty: Unit = {
        val x = unfoldRight(5, { (b: Int) => None })
        detail.TestEmpty(x.eval)
    }
}
