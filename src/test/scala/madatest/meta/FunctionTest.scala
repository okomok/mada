

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class FunctionTest {
    def testNone: Unit = ()

    type incre[n <: Nat] = n#increment
    type inc = quote1[incre, Nat, Nat]

    trait incf extends Function1 {
        type Argument11 = Nat
        type Result1 = Nat
        type apply1[v1 <: Argument11] = v1#increment
    }

    //type Func1[T1, R] = Function1 { type Result1 = R; type Argument11 = T1 }//; type apply1[v <: T1] <: R }

    type twice[f <: Func1[Nat, Nat], n <: Nat] = f#apply1[n]#increment

    trait testTrivial {
        assert[forwarding1[inc]#apply1[_3N] == _4N]
    //    assert[twice[incf, _3N] == _5N]
    //    assert[twice[inc, _3N] == _5N]
    }
}
