

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// TODO: make it easy method impl.

private[mada] class Size {
     def apply[xs <: List](xs: xs): apply[xs] = xs.foldRight(nat.Peano._0, Step()).asInstanceOfNatPeano
    type apply[xs <: List] = xs#foldRight[nat.Peano._0, Step]#asInstanceOfNatPeano

    final case class Step() extends Function2 {
        override  def self = this
        override type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNatPeano.increment
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNatPeano#increment
    }
}
