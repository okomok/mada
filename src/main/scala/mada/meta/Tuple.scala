

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


// classes

abstract class Tuple0 extends Product0 {
}

abstract class Tuple1[T1, v1 <: T1] extends Product1 {
    override type _T1 = T1

    override type _1 = v1
}

abstract class Tuple2[T1, T2, v1 <: T1, v2 <: T2] extends Product2 {
    override type _T1 = T1
    override type _T2 = T2

    override type _1 = v1
    override type _2 = v2
}

abstract class Tuple3[T1, T2, T3, v1 <: T1, v2 <: T2, v3 <: T3] extends Product3 {
    override type _T1 = T1
    override type _T2 = T2
    override type _T3 = T3

    override type _1 = v1
    override type _2 = v2
    override type _3 = v3
}
