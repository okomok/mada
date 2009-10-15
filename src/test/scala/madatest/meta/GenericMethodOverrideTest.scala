

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class GenericMethodOverrideTest {

    trait B {
        type foo[T, x <: T] <: T // generic metamethod
    }

    trait d extends B {
        override type foo[T, x <: T] = x
    }

    // OK
    type foo1[b <: B, x <: Nat] = b#foo[Nat, x]
    assertSame[foo1[d, _2N]#increment, _3N]

    // NO
    type foo2[b <: B, x <: Nat] = b#foo[Nat, x]#increment
    //assertSame[foo2[d, _2N], _3N]

}
