

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


// 1. `object Add` crashes `Operator`.
// 2. `case class Add[x <: Peano, y <: Peano](x: x, y: y)` may fall into type mismatch and slow-compilation.
@compilerWorkaround("2.8.0")
private[mada] final class Add {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = x.foldRight(y, new Step).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = x#foldRight[y, Step]#asInstanceOfNatPeano

    class Step extends Function2 {
        override  val self = this
        override type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNatPeano.increment
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNatPeano#increment
    }
}
