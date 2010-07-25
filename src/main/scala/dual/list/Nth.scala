

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsNthOption {
     def apply[x <: Any, xs <: List, n <: Nat](x: x, xs: xs, n: n): apply[x, xs, n] =
        `if`(n.isZero, Const0(Some(x)), Else(xs, n)).apply.asInstanceOfOption//.asInstanceOf[apply[x, xs, n]]
    type apply[x <: Any, xs <: List, n <: Nat] =
        `if`[n#isZero, Const0[Some[x]], Else[xs, n]]#apply#asInstanceOfOption

    case class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
        type self = Else[xs, n]
        override  def apply: apply = xs.nthOption(n.decrement)
        override type apply = xs#nthOption[n#decrement]
    }
}
