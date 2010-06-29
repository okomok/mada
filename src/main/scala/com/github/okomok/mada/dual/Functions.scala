

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


trait Function0_Any {
     def apply: apply
    type apply <: Any
}

trait Function0_Boolean {
     def apply: apply
    type apply <: Boolean
}

trait Function0_Nat {
     def apply: apply
    type apply <: Nat
}


trait Function2_Nat_Any_Any {
     def apply[v1 <: Nat, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Nat, v2 <: Any] <: Any
}

trait Function2_Nat_Nat_Nat {
     def apply[v1 <: Nat, v2 <: Nat](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Nat, v2 <: Nat] <: Nat
}
