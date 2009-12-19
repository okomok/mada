

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


trait Function0 {
    type apply
}

trait Function1 {
    type apply[v1 <: T1]
}

trait Function2 {
    type apply[v1 <: T1, v2 <: T2]
}

trait Function3 {
    type apply[v1 <: T1, v2 <: T2, v3 <: T3]
}
