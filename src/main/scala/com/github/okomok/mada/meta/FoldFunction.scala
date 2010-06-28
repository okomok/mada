

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta


trait FoldFunction[T] {
    type apply[v1 <: T, v2 <: T] <: T
}
