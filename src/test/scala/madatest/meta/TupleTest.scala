

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class TupleTest {

    // "types"
    trait Strong extends Object
    trait Strung extends Object

    // "objects"
    sealed trait so1 extends Strong
    sealed trait su1 extends Strung

    sealed trait so2 extends Strong
    sealed trait su2 extends Strung

    type id[p <: Product2 { type _T1 <: Strong; type _T2 <: Strung }] = pair[p#_1[_], p#_2[_]]//Tuple2[Strong, Strung, p#_1[_], p#_2[_]]
    type pair_[t1 <: Object, t2 <: Object] = Tuple2[t1, t2, t1, t2]

    // "objects"
    sealed trait ok1 extends id[pair_[so1, su1]]
    sealed trait ok2 extends id[pair_[so2, su2]]
    sealed trait ok3_ extends id[pair_[so1, su2]]
    sealed trait ok3  extends
        id[ok3_]
        //id[id[pair_[so1, su2]]]

    type ok3_1 = ok3#_1[_]
    assertSame[ok3_1, so1]

   // assertSame[id[pair_[so1, su1]]#_1[_], so1]

    assertSame[ok1#_1[_], so1]
    assertSame[ok2#_2[_], su2]

    assertSame[ok3#_1[_], so1]
    assertSame[ok3#_1[_], so1]
    assertSame[ok3#_2[_], su2]

    /*
    // type alias(that is, meta methods) results in illegal cyclic reference...
    type ok1[_] = id[pair_[so1, su1]]
    type ok2[_] = id[pair_[so2, su2]]
    type ok3_[_] = id[pair_[so1, su2]]
    type ok3[_]  =
        id[ok3_[_]]
        //id[id[pair_[so1, su2]]]

    type ok3_1[_] = ok3[_]#_1[_]
    assertSame[ok3_1[_], so1]

   // assertSame[id[pair_[so1, su1]]#_1[_], so1]

    assertSame[ok1[_]#_1[_], so1]
    assertSame[ok2[_]#_2[_], su2]

    assertSame[ok3[_]#_1[_], so1]
    assertSame[ok3[_]#_1[_], so1]
    assertSame[ok3[_]#_2[_], su2]
    */

}
