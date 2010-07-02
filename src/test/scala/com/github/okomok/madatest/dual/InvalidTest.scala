

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.meta._
// import junit.framework.Assert._


// Overriding generic-metamethod doesn't work.

object GenericMethodOverrideTezt {

    trait B {
        type foo[T, x <: T] <: T // "generic" metamethod (ie, result metatype depends on arguments.)
    }

    trait d extends B {
        override type foo[T, x <: T] = x
    }

    // OK
    type foo1[b <: B, x <: Nat] = b#foo[Nat, x]
    assertSame[foo1[d, _2N]#increment, _3N]

    // NO
    type foo2[b <: B, x <: Nat] = b#foo[Nat, x]#increment
    // assertSame[foo2[d, _2N], _3N]

    // NO
    type foo3[b <: B { type foo[Nat, x <: Nat] <: Nat }, x <: Nat] = b#foo[Nat, x]#increment
    type wow = foo3[d, _2N] // Scala is smart!
    // assertSame[wow, _3N] // but fails.
}

object GenericMethodOverride2Tezt {

    trait B {
        type foo[x]
    }

    trait d extends B {
        override type foo[x] = _3N
    }

    // OK
    type foo1[b <: B, x <: Nat] = b#foo[x]
    assertSame[foo1[d, _2N]#increment, _4N]

    // NO, of course.
    // type foo2[b <: B, x <: Nat] = b#foo[x]#increment
}



object TypeConstraintTezt {

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

object TypeConstraint2Tezt {

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

object TypeConstraint3Tezt {

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


object TypeConstraint4Tezt {

    type inc[n <: Nat] = n#increment

    // OK
    type foo1[_inc[_ <: Nat] <: Nat, n <: Nat] = _inc[n]
    assertSame[foo1[inc, _2N]#increment, _4N]

    // OK
    type foo2[_inc[_ <: Nat] <: Nat, n <: Nat] = _inc[n]#increment
    assertSame[foo2[inc, _2N], _4N]

}


object TypeConstraint5Tezt {

    trait B[R] {
        type inc[n <: Nat] <: R
    }

    trait d extends B[Nat] {
        override type inc[n <: Nat] = n#increment
    }

    // OK
    type foo1[b <: B[Nat], n <: Nat] = b#inc[n]
    assertSame[foo1[d, _2N]#increment, _4N]

    // OK (restate constraint!)
    // See also: http://lampsvn.epfl.ch/trac/scala/ticket/1786
    type foo2[b <: B[_] { type inc[n <: Nat] <: Nat }, n <: Nat] = b#inc[n]#increment
    assertSame[foo2[d, _2N], _4N]

}


object TypeConstraint6Tezt {

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
    trait Base {
        type foo2[b <: B[Nat], n <: Nat] <: Nat
    }
    trait Derived extends Base {
        override type foo2[b <: B[Nat], n <: Nat] = b#inc[n]#increment
    }
    type callfoo2[x <: Base, b <: B[Nat], n <: Nat] = x#foo2[b, n]
    //assertSame[callfoo2[Derived, d, _2N], _4N]

}
