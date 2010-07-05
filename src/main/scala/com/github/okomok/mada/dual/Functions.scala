

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


trait Function0 extends Any {
     def apply: apply
    type apply <: Any
}

trait Function1 extends Any {
     def apply[v1 <: Any](v1: v1): apply[v1]
    type apply[v1 <: Any] <: Any
}

trait Function2 extends Any {
     def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: Any] <: Any
}
