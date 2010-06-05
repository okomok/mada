

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package metatest


import com.github.okomok.mada

import mada.meta._
// import junit.framework.Assert._


object TupleTezt {

    // "types"
    trait Strong
    trait Strung

    // "objects"
    trait so1 extends Strong
    trait su1 extends Strung

    trait so2 extends Strong
    trait su2 extends Strung

    type id[p <: Product2 { type _1 <: Strong; type _2 <: Strung }] = pair[p#_1, p#_2]

    type ok1 = id[pair[so1, su1]]
    type ok2 = id[pair[so2, su2]]
    type ok3_ = id[pair[so1, su2]]
    type ok3  =
        id[ok3_]
        //id[id[pair_[so1, su2]]]

    type ok3_1 = ok3#_1
    assertSame[ok3_1, so1]

   // assertSame[id[pair_[so1, su1]]#_1, so1]

    assertSame[ok1#_1, so1]
    assertSame[ok2#_2, su2]

    assertSame[ok3#_1, so1]
    assertSame[ok3#_1, so1]
    assertSame[ok3#_2, su2]


    // Hmm...
    import nat.Literal._
    type get2inc[t <: tuple2[_, _ <: Nat] { type _2 <: Nat }] = t#_2#increment
    //assertSame[get2inc[tuple2[so1, _3N]], _4N] // fails.

    // OK
    type _get2inc[t <: Product2 { type _2 <: Nat }] = t#_2#increment
    assertSame[_get2inc[tuple2[so1, _3N]], _4N]

}
