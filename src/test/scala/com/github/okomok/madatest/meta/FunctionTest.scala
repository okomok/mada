

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package metatest


import com.github.okomok.mada

import mada.meta._
// import junit.framework.Assert._


object FunctionTest {

    trait Func1 {
        type Arg1
        type Result
        type apply[v1 <: Arg1] <: Result
    }
    sealed trait _quote1[T1, R1, f[_ <: T1] <: R1] extends Func1 {
        override type Arg1 = T1
        override type Result = R1
        override type apply[v1 <: T1] = f[v1]
    }

    type incre[n <: Nat] = n#increment
    type inc = quote1[incre, Nat]

    trait incf extends Function1 {
        override type Arg1 = Nat
        override type apply[v1 <: Nat] = v1#increment
    }

    type FuncNat = /*Function1*/ {
        type Arg1 = Nat; type apply[v <: Arg1] <: Nat
    }

    type applyx[f <: Function1 { type Arg1 = Nat }, n <: Nat] = f#apply[n]
    type twice[f <: FuncNat, n <: Nat] = f#apply[n]#increment

    trait testTrivial {
        assert[forwarding1[Nat, incre]#apply[_3N] == _4N]
        assert[applyx[incf, _3N] == _4N]
        assert[applyx[inc, _3N] == _4N]

        assert[twice[incf, _3N] == _5N]

        //type k = twice[inc, _3N] // error
        //assert[k == _5N]
    }
}
