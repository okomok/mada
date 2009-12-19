

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class FunctionTest {
    def testNone: Unit = ()

    type incre[n <: Nat] = n#increment
    type inc = quote1[incre, Nat]

    trait incf extends Function1 {
        type apply[v1 <: Nat] = v1#increment
    }

    type applyx[f <: Function1 { type apply[v <: Nat] <: Nat }, n <: Nat] = f#apply[n]
 //   type applyw[f <: Func1[Nat, Nat], n <: Nat] = f#apply[n]

 //   type twice[f <: Func1[Nat, Nat], n <: Nat] = f#apply[n]#increment

    trait testTrivial {
        assert[forwarding1[inc]#apply[_3N] == _4N]
   //     assert[applyw[incf, _3N] == _4N]
        assert[applyx[incf, _3N] == _4N]

        assert[applyx[inc, _3N] == _4N]

    //    assert[twice[incf, _3N] == _5N] // error
    //    assert[twice[inc, _3N] == _5N] // error
    }
}
