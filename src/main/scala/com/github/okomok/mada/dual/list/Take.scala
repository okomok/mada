

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// cf. take(n) = reverse.drop(size - n).reverse


private[mada] final case class Take[xs <: List, n <: nat.Peano](xs: xs, n: n) {
     def apply: apply = `if`(n.isZero || xs.isEmpty, always0(Nil), Else()).apply.asInstanceOfList.asInstanceOf[apply]
    type apply = `if`[n#isZero# ||[xs#isEmpty], always0[Nil], Else]#apply#asInstanceOfList

    final case class Else() extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = Cons(xs.head, Take(xs.tail, n.decrement).apply).asInstanceOf[apply]
        override type apply = Cons[xs#head, Take[xs#tail, n#decrement]#apply]
    }
}
