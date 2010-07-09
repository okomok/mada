

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] class Drop {
     def apply[xs <: List, n <: nat.Peano](xs: xs, n: n): apply[xs, n] = n.foldRight(xs, Step()).asInstanceOfList
    type apply[xs <: List, n <: nat.Peano] = n#foldRight[xs, Step]#asInstanceOfList

    final case class Step() extends Function2 {
        override  def self = this
        override type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfList.tail
        override type apply[a <: Any, b <: Any] = b#asInstanceOfList#tail
    }
}


/*
// After all, `if` is a destroyer.
private[mada] class Drop extends Function2_List_Nat_List {
    override def apply[xs <: List, n <: nat.Peano](xs: xs, n: n): apply[xs, n] = if_List(new Cond(xs, n).apply, new always0_List(xs), new Else(xs, n, this)).apply
    override type apply[xs <: List, n <: nat.Peano] = if_List[Cond[xs, n]#apply, always0_List[xs], Else[xs, n, Drop]]#apply

    @compilerWorkaround("2.8.0") // works around a type mismatch.
    class Cond[xs <: List, n <: nat.Peano](xs: xs, n: n) extends Function0_Boolean {
        override  def apply: apply = xs.isEmpty || n === nat.peano._0 // order matters!
        override type apply = xs#isEmpty# ||[n# ===[nat.peano._0]]
    }

    class Else[xs <: List, n <: nat.Peano, recur <: Function2_List_Nat_List](xs: xs, n: n, recur: recur) extends Function0_List {
        override  def apply: apply = recur.apply(xs, n.decrement)
        override type apply = recur#apply[xs, n#decrement]
    }
}
*/
