

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta tuples.
 */
trait Tuples { this: Meta.type =>


// classes

    trait Tuple0 extends Product0 {
    }

    trait Tuple1[T1 <: Object, v1 <: T1] extends Product1 {
        override type _T1 = T1

        override type _1 = v1
    }

    trait Tuple2[T1 <: Object, T2 <: Object, v1 <: T1, v2 <: T2] extends Product2 {
        override type _T1 = T1
        override type _T2 = T2

        override type _1 = v1
        override type _2 = v2
    }

    trait Tuple3[T1 <: Object, T2 <: Object, T3 <: Object, v1 <: T1, v2 <: T2, v3 <: T3] extends Product3 {
        override type _T1 = T1
        override type _T2 = T2
        override type _T3 = T3

        override type _1 = v1
        override type _2 = v2
        override type _3 = v3
    }


// constructors for type inference

    type tuple0 = Tuple0
    type tuple1[t1 <: Object] = Tuple1[t1, t1]
    type typle2[t1 <: Object, t2 <: Object] = Tuple2[t1, t2, t1, t2]
    type typle3[t1 <: Object, t2 <: Object, t3 <: Object] = Tuple3[t1, t2, t3, t1, t2, t3]


// pair

    type Pair[T1 <: Object, T2 <: Object, v1 <: T1, v2 <: T2] = Tuple2[T1, T2, v1, v2]
    type pair[t1 <: Object, t2 <: Object] = Pair[t1, t2, t1, t2]

}
