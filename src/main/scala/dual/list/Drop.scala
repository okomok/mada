

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class Drop {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] =
        `if`(n.isZero || xs.isEmpty, const0(xs), Else(xs, n)).apply.asInstanceOfList.asInstanceOf[apply[xs, n]]
    type apply[xs <: List, n <: Nat] =
        `if`[n#isZero# ||[xs#isEmpty], const0[xs], Else[xs, n]]#apply#asInstanceOfList

    case class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
        type self = Else[xs, n]
        override  def apply: apply = xs.tail.drop(n.decrement).asInstanceOf[apply]
        override type apply = xs#tail#drop[n#decrement]
    }
}
