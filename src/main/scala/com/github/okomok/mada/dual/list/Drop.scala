

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list



/*
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
*/


private[mada] final case class Drop[xs <: List, n <: nat.Peano](xs: xs, n: n) {
     def apply: apply = `if`(n.isZero || xs.isEmpty, always0(xs), Else()).apply.asInstanceOfList.asInstanceOf[apply]
    type apply = `if`[n#isZero# ||[xs#isEmpty], always0[xs], Else]#apply#asInstanceOfList

    final case class Else() extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = Drop(xs.tail, n.decrement).apply.asInstanceOf[apply]
        override type apply = Drop[xs#tail, n#decrement]#apply
    }
}
