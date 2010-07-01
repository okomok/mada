

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

trait Function0_List {
     def apply: apply
    type apply <: List
}

trait Function0_Unit {
     def apply: apply
    type apply <: Unit
}


trait Function2_Any_Any_Any {
     def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: Any] <: Any
}

trait Function2_Any_Nat_Nat {
     def apply[v1 <: Any, v2 <: Nat](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: Nat] <: Nat
}

trait Function2_Any_List_List {
     def apply[v1 <: Any, v2 <: List](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Any, v2 <: List] <: List
}


trait Function2_Nat_Any_Any {
     def apply[v1 <: Nat, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Nat, v2 <: Any] <: Any
}

trait Function2_Nat_Nat_Nat {
     def apply[v1 <: Nat, v2 <: Nat](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Nat, v2 <: Nat] <: Nat
}

trait Function2_Nat_List_List {
     def apply[v1 <: Nat, v2 <: List](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: Nat, v2 <: List] <: List
}


trait Function2_List_Any_List {
     def apply[v1 <: List, v2 <: Any](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: List, v2 <: Any] <: List
}

trait Function2_List_Nat_List {
     def apply[v1 <: List, v2 <: Nat](v1: v1, v2: v2): apply[v1, v2]
    type apply[v1 <: List, v2 <: Nat] <: List
}
