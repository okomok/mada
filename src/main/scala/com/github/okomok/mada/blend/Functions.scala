

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend


trait Function1[T1, R] extends meta.Function1[T1, R] {
    def apply[v1 <: T1](_v1: v1): apply[v1]
}

trait Function2[T1, T2, R] extends meta.Function2[T1, T2, R] {
    def apply[v1 <: T1, v2 <: T2](_v1: v1, _v2: v2): apply[v1, v2]
}
