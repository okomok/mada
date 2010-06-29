

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


trait Function0[R] {
    def apply: apply
    type apply <: R
}

trait Function1[T1, R] {
    def apply[v1 <: T1](v1: v1): apply[v1]
    type apply[v1 <: T1] <: R
}

trait Function2[T1, T2, R] {
    def apply[v1 <: T1, v2 <: T2](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: T1, v2 <: T2] <: R
}

trait Function3[T1, T2, T3, R] {
    def apply[v1 <: T1, v2 <: T2, v3 <: T3](v1: v1, v2: v2, v3: v3): apply[v1, v2, v3]
    type apply[v1 <: T1, v2 <: T2, v3 <: T3] <: R
}
