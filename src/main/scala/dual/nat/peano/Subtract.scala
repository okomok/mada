

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final class Subtract {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = y.foldRight(x, Step()).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = y#foldRight[x, Step]#asInstanceOfNatPeano

    case class Step() extends Function2 {
        override  val self = this
        override type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNatPeano.decrement
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNatPeano#decrement
    }
}
