

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] class Drop {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = n.foldRight_List(xs, step)
    type apply[xs <: List, n <: Nat] = n#foldRight_List[xs, step]

    val step = new step
    class step extends Function2_Nat_List_List {
        override def apply[a <: Nat, b <: List](a: a, b: b): apply[a, b] = b.tail
        override type apply[a <: Nat, b <: List] = b#tail
    }
}


/*
// After all, `if` is a destroyer.
private[mada] class Drop extends Function2_List_Nat_List {
    override def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = if_List(new Cond(xs, n).apply, new Always0_List(xs), new Else(xs, n, this)).apply
    override type apply[xs <: List, n <: Nat] = if_List[Cond[xs, n]#apply, Always0_List[xs], Else[xs, n, Drop]]#apply

    @compilerWorkaround("2.8.0") // works around a type mismatch.
    class Cond[xs <: List, n <: Nat](xs: xs, n: n) extends Function0_Boolean {
        override  def apply: apply = xs.isEmpty || n === _0N // order matters!
        override type apply = xs#isEmpty# ||[n# ===[_0N]]
    }

    class Else[xs <: List, n <: Nat, recur <: Function2_List_Nat_List](xs: xs, n: n, recur: recur) extends Function0_List {
        override  def apply: apply = recur.apply(xs, n.decrement)
        override type apply = recur#apply[xs, n#decrement]
    }
}
*/
