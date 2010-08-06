

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
final class Multiply {
    // fold in y, for `**` is left-associative.
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] = y.foldRight(Zero, Step(x)).asInstanceOfNatPeano
    type apply[x <: Peano, y <: Peano] = y#foldRight[Zero, Step[x]]#asInstanceOfNatPeano

    case class Step[x <: Peano](x: x) extends Function2 {
        type self = Step[x]
        override def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = x + b.asInstanceOfNatPeano
        override type apply[a <: Any, b <: Any] = x# +[b#asInstanceOfNatPeano]
    }
}
