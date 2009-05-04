

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class ForwardingTest {
    def testNone: Unit = ()

    type incre[n <: Int] = n#increment
    type inc = quote1[incre, Int, Int]

    trait testTrivial {
        assert[forwarding1[inc]#apply1[_3I] == _4I]
    }
}
