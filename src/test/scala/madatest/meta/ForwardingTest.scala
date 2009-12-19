

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class ForwardingTest {
    def testNone: Unit = ()

    type incre[n <: Nat] = n#increment
    type inc = quote1[incre, Nat]

    type foo[n <: Nat] = forwarding1[inc]#apply[n]#increment

    trait testTrivial {
        assert[foo[_3N] == _5N]
    }
}
