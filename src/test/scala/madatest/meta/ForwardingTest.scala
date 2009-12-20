

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class ForwardingTest {
    def testNone: Unit = ()

    type incre[n <: Nat] = n#increment

    type foo[n <: Nat] = forwarding1[Nat, incre]#apply[n]#increment

    trait testTrivial {
        assert[foo[_3N] == _5N]
    }
}
