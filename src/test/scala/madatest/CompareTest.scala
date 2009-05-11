

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.{compare, Compare}
import junit.framework.Assert._


class CompareTest {
    def takeLambda[A](x: A)(c: Compare[A]): Unit = ()

    def testTrivial: Unit = {
        takeLambda(3)(compare.by[Int](_ < _))
    }
}
