

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


case class Tuple2[v1 <: Any, v2 <: Any](_1: v1, _2: v2) extends Any {
    type _1 = v1
    type _2 = v2
}
