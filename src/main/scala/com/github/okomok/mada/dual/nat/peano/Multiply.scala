

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] class Multiply {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = x.foldRight(Zero, Step(y)).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = x#foldRight[Zero, Step[y]]#asInstanceOfNatPeano

    final case class Step[y <: Peano](y: y) extends Function2 {
        override  val self = this
        override type self = Step[y]
        override def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = y + b.asInstanceOfNatPeano
        override type apply[a <: Any, b <: Any] = y# +[b#asInstanceOfNatPeano]
    }
}
