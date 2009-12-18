

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


// Generic-metamethod can't be overridden.

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



// In summary, metatrait can't be parametarized.

class TypeConstraintTest {

    trait B {
        type R
        type inc[n <: Nat] <: R
    }

    trait d extends B {
        type R = Nat
        override type inc[n <: Nat] = n#increment
    }

    // OK
    type foo1[b <: B { type R = Nat }, n <: Nat] = b#inc[n]
    assertSame[foo1[d, _2N]#increment, _4N]

    // NO
    type foo2[b <: B { type R = Nat }, n <: Nat] = b#inc[n]#increment
    // assertSame[foo2[d, _2N], _4N]

}

class TypeConstraint2Test {

    trait B[R] {
        type inc[n <: Nat] <: R
    }

    trait d extends B[Nat] {
        override type inc[n <: Nat] = n#increment
    }

    // OK
    type foo1[b <: B[Nat], n <: Nat] = b#inc[n]
    assertSame[foo1[d, _2N]#increment, _4N]

    // NO
    type foo2[b <: B[Nat], n <: Nat] = b#inc[n]#increment
    // assertSame[foo2[d, _2N], _4N]

}

class TypeConstraint3Test {

    trait B {
        type inc[n <: Nat] <: Nat
    }

    trait d extends B {
        override type inc[n <: Nat] = n#increment
    }

    // OK
    type foo1[b <: B, n <: Nat] = b#inc[n]
    assertSame[foo1[d, _2N]#increment, _4N]

    // OK
    type foo2[b <: B, n <: Nat] = b#inc[n]#increment
    assertSame[foo2[d, _2N], _4N]

}


class TypeConstraint4Test {

    type inc[n <: Nat] = n#increment

    // OK
    type foo1[_inc[_ <: Nat] <: Nat, n <: Nat] = _inc[n]
    assertSame[foo1[inc, _2N]#increment, _4N]

    // OK
    type foo2[_inc[_ <: Nat] <: Nat, n <: Nat] = _inc[n]#increment
    assertSame[foo2[inc, _2N], _4N]

}
