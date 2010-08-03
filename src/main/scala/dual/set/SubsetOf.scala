

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[mada] final class SubsetOf {
     def apply[s <: Set, z <: Set](s: s, z: z): apply[s, z] = s.toSeq.forall(Pred(z)).asInstanceOf[apply[s, z]]
    type apply[s <: Set, z <: Set] = s#toSeq#forall[Pred[z]]

    case class Pred[z <: Set](z: z) extends Function1 {
        type self = Pred[z]
        override  def apply[k <: Any](k: k): apply[k] = z.contains(k)
        override type apply[k <: Any] = z#contains[k]
    }
}
