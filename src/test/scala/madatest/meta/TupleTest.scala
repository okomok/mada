

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.metatest


import mada.meta._
// import junit.framework.Assert._


class TupleTest {

    // "types"
    trait Strong
    trait Strung

    // "objects"
    trait so1 extends Strong
    trait su1 extends Strung

    trait so2 extends Strong
    trait su2 extends Strung

    type id[p <: Product2 { type _T1 <: Strong; type _T2 <: Strung }] = pair[p#_1, p#_2]//Tuple2[Strong, Strung, p#_1, p#_2]
    type pair_[t1, t2] = Tuple2[t1, t2, t1, t2]

    type ok1 = id[pair_[so1, su1]]
    type ok2 = id[pair_[so2, su2]]
    type ok3_ = id[pair_[so1, su2]]
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

}
