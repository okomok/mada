

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package typeidentity2test


import com.github.okomok.mada

import mada.dual.free.assertSame
//import junit.framework.Assert._


sealed trait Nat {
    type `this` <: Nat

    type increment <: Nat
    type add[that <: Nat] <: Nat

    type accept[v <: Visitor] <: v#Result

    type acceptOf[R, v <: VisitorOf[R]] <: R // Nothing changes..... cuz this is generic.

    type acceptNat[v <: VisitorOf[Nat]] <: Nat // OK. not generic.
}

trait Visitor {
    type Result // ABSTRACT RESULT TYPE does the bad thing.
    type visitZero <: Result
    type visitSucc[n <: Nat] <: Result
}

trait VisitorOf[R] {
    type visitZero <: R
    type visitSucc[n <: Nat] <: R
}

sealed trait Zero extends Nat {
    override type `this` = Zero
    override type increment = Succ[Zero]
    override type add[that <: Nat] = that
    override type accept[v <: Visitor] = v#visitZero

    override type acceptOf[R, v <: VisitorOf[R]] = v#visitZero

    override type acceptNat[v <: VisitorOf[Nat]] = v#visitZero
}

sealed trait Succ[n <: Nat] extends Nat {
    override type `this` = Succ[n]
    override type increment = Succ[`this`]
    override type add[that <: Nat] = Succ[n#add[that]]
    override type accept[v <: Visitor] = v#visitSucc[n]

    override type acceptOf[R, v <: VisitorOf[R]] = v#visitSucc[n]

    override type acceptNat[v <: VisitorOf[Nat]] = v#visitSucc[n]
}


object TypeIdentity2StaticTezt {

    type _0N = Zero
    type _1N = Succ[_0N]
    type _2N = Succ[_1N]
    type _3N = Succ[_2N]
    type _4N = Succ[_3N]
    type _5N = Succ[_4N]
    type _6N = Succ[_5N]
    type _7N = Succ[_6N]
    type _8N = Succ[_7N]
    type _9N = Succ[_8N]
    type _10N = Succ[_9N]


    trait vt[m <: Nat] extends Visitor {
        type Result = Nat
        type visitZero = m
        type visitSucc[n <: Nat] = n#accept[vt[Succ[m]]]
    }

    trait test1 {
        type goo[n <: Nat] = _2N#accept[vt[n]]#increment
        type goh[n <: Nat] = _2N#accept[vt[n]]
        assertSame[goh[_3N]#increment, _6N]
        assertSame[goo[_3N], _6N] // ok!!!!!!!!!
    }

    trait test2 {
        type foo[n <: Nat] = n#accept[vt[_2N]]#increment
        type foh[n <: Nat] = n#accept[vt[_2N]]
        assertSame[foh[_3N]#increment, _6N]
       // assertSame[foo[_3N], _6N] // error
    }

    trait vtOf[m <: Nat] extends VisitorOf[Nat] {
        type visitZero = m
        type visitSucc[n <: Nat] = n#accept[vt[Succ[m]]]
    }

    trait test3 {
        type foo[n <: Nat] = n#acceptOf[Nat, vtOf[_2N]]#increment
        type foh[n <: Nat] = n#acceptOf[Nat, vtOf[_2N]]
        assertSame[foh[_3N]#increment, _6N]
        // assertSame[foo[_3N], _6N] // error
    }

    trait testOk {
        type foo[n <: Nat] = n#acceptNat[vtOf[_2N]]#increment
        type foh[n <: Nat] = n#acceptNat[vtOf[_2N]]
        assertSame[foh[_3N]#increment, _6N]
        assertSame[foo[_3N], _6N] // OK!!!
    }

    trait test21 {
        // n#accept[vt[_2N]]
        // -> vt[_2N]#visitSucc[n-1]
        // -> n-1#accept[vt[_2N+1]]
        // ...
    }

    type add[n <: Nat, m <: Nat] = n#accept[vt[m]]

    type add1[n <: Nat, m <: Nat] = n#accept[vt[m]]#increment // doesn't work.
    //assertSame[add1[_3N, _2N], _6N] // error

    type add2[n <: Nat, m <: Nat, k <: Nat] = n#accept[vt[m]]#add[k] // doesn't work.
    assertSame[add[_3N, _2N]#add[_4N], _9N]
//    assertSame[add2[_3N, _2N, _4N], _9N] // error


}
