

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[dual]
object ConsExp {
     def apply[x <: Dense, n <: Nat](x: x, n: n): apply[x, n] = n.asNatPeano.foldRight(_1, Step(x)).asNatDense
    type apply[x <: Dense, n <: Nat]                          = n#asNatPeano#foldRight[_1, Step[x]]#asNatDense

    case class Step[x <: Dense](x: x) extends Function2 {
        type self = Step[x]
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = x.times(b.asNatDense)
        override type apply[a <: Any, b <: Any]                          = x#times[b#asNatDense]
    }
}
