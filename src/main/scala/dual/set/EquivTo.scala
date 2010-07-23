

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[mada] final class EquivTo {
     def apply[s <: Set, z <: Set](s: s, z: z): apply[s, z] =
        `if`(s.size  !== z.size,  const0(`false`), Else(s, z)).apply.asInstanceOf[apply[s, z]]
    type apply[s <: Set, z <: Set] =
        `if`[s#size# !==[z#size], const0[`false`], Else[s, z]]#apply

    case class Else[s <: Set, z <: Set](s: s, z: z) extends Function0 {
        override  val self = this
        override type self = Else[s, z]
        override  def apply: apply = s.toList.forall(Pred(z))
        override type apply        = s#toList#forall[Pred[z]]
    }

    case class Pred[z <: Set](z: z) extends Function1 {
        override  val self = this
        override type self = Pred[z]
        override  def apply[k <: Any](k: k): apply[k] = z.contains(k)
        override type apply[k <: Any]                 = z#contains[k]
    }
}
