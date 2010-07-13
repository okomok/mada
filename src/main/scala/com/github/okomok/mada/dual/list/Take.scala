

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// cf. take(n) = reverse.drop(size - n).reverse


private[mada] final class Take {
     def apply[xs <: List, n <: nat.Peano](xs: xs, n: n): apply[xs, n] = `if`(n.isZero || xs.isEmpty, always0(Nil), new Else(xs, n)).apply.asInstanceOfList.asInstanceOf[apply[xs, n]]
    type apply[xs <: List, n <: nat.Peano] = `if`[n#isZero# ||[xs#isEmpty], always0[Nil], Else[xs, n]]#apply#asInstanceOfList

    class Else[xs <: List, n <: nat.Peano](xs: xs, n: n) extends Function0 {
        override  val self = this
        override type self = Else[xs, n]
        override  def apply: apply = Cons(xs.head, xs.tail.take(n.decrement)).asInstanceOf[apply]
        override type apply = Cons[xs#head, xs#tail#take[n#decrement]]
    }
}
