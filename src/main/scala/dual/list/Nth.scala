

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsNth {
     def apply[x <: Any, xs <: List, n <: Nat](x: x, xs: xs, n: n): apply[x, xs, n] =
        `if`(n.isZero, Const0(x), Else(xs, n)).apply//.asInstanceOf[apply[x, xs, n]]
    type apply[x <: Any, xs <: List, n <: Nat] =
        `if`[n#isZero, Const0[x], Else[xs, n]]#apply

    case class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
        type self = Else[xs, n]
        override  def apply: apply = xs.nth(n.decrement)
        override type apply = xs#nth[n#decrement]
    }
}
