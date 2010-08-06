

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


// 1. `object Add` crashes `Operator`.
// 2. `case class Add[x <: Peano, y <: Peano](x: x, y: y)` may fall into type mismatch and slow-compilation.
@compilerWorkaround("2.8.0")
private[dual]
final class Add {
    // fold in y, for `+` is left-associative.
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = y.foldRight(x, Step()).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = y#foldRight[x, Step]#asInstanceOfNatPeano

    case class Step() extends Function2 {
        type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNatPeano.increment
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNatPeano#increment
    }
}
