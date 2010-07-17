

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class NthOption {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] =
        `if`(n >= xs.size, const0(None), Else(xs, n)).apply.asInstanceOfOption.asInstanceOf[apply[xs, n]]
    type apply[xs <: List, n <: Nat] =
        `if`[n# >=[xs#size], const0[None], Else[xs, n]]#apply#asInstanceOfOption

    case class Else[xs <: List, n <: Nat](xs: xs, n: n) extends Function0 {
        override  val self = this
        override type self = Else[xs, n]
        override  def apply: apply = Some(xs.drop(n).head)
        override type apply = Some[xs#drop[n]#head]
    }
}
