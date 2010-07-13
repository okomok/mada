

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] class Subtract {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = y.foldRight(x, step).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = y#foldRight[x, step]#asInstanceOfNatPeano

    val step = new step
    final class step extends Function2 {
        override  val self = this
        override type self = step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNatPeano.decrement
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNatPeano#decrement
    }
}
