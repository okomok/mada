

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
object Minus {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = y.foldRight(x, Step).asNatPeano
    type apply[x <: Peano, y <: Peano]                          = y#foldRight[x, Step]#asNatPeano

    val Step = new Step
    class Step extends Function2 {
        type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asNatPeano.decrement
        override type apply[a <: Any, b <: Any]                          = b#asNatPeano#decrement
    }
}
