

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta


trait Function1[T1, R] {
    type apply[v1 <: T1] <: R
}

trait Function2[T1, T2, R] {
    type apply[v1 <: T1, v2 <: T2] <: R
}
