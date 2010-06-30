

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list

/*
private[mada] object ConsTake {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = if_List(n === _0N, new Always0_Nil, new Else(xs, n)).apply
    type apply[xs <: List, n <: Nat] = if_List[n === _0N, Always0_Nil, Else[xs, n]]#apply

    class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0_List {
        override  def apply = Cons(xs.head, xs.tail.take(n.decrement))
        override type apply = Cons[xs#head, xs#tail#take[n#decrement]]
    }
}
*/


private[mada] object Take {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = if_List(new Cond(xs, n).apply, new Always0_Nil, new Else(xs, n)).apply
    type apply[xs <: List, n <: Nat] = if_List[Cond[xs, n]#apply, Always0_Nil, Else[xs, n]]#apply

    class Cond[xs <: List, n <: Nat](xs: xs, n: n) extends Function0_Boolean {
        override  def apply: apply = xs.isEmpty || n === _0N
        override type apply = xs#isEmpty# ||[n# ===[_0N]]
    }

    class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0_List {
        override  def apply: apply = Cons(xs.head, Take.apply(xs.tail, n.decrement))
        override type apply = Cons[xs#head, Take.apply[xs#tail, n#decrement]]
    }
}
