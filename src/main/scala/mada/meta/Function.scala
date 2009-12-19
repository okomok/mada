

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


trait Function0 {
    type apply
}

trait Function1 {
    type __T1

    type apply[v1 <: __A1]
}

trait Function2 {
    type __T1
    type __T2

    type apply[v1 <: __T1, v2 <: __T2]
}

trait Function3 {
    type __T1
    type __T2
    type __T3

    type apply[v1 <: __T1, v2 <: __T2, v3 <: __T3]
}
