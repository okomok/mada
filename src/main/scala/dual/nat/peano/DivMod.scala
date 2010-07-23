

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final class DivMod {
    @nothingTypeMismatchWorkaround
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] =
        `if`(x < y, const0(Tuple2(Zero, x)), Else(x, y)).apply.asInstanceOfProduct2
    type apply[x <: Nat, y <: Nat] =
        `if`[x# <[y], const0[Tuple2[Zero, x]], Else[x, y]]#apply#asInstanceOfProduct2

    case class Else[x <: Nat, y <: Nat](x: x, y: y) extends Function0 {
        override  val self = this
        override type self = Else[x, y]
        override  def apply: apply = Tuple2(r._1.asInstanceOfNat.increment, r._2)
        override type apply = Tuple2[r#_1#asInstanceOfNat#increment, r#_2]
        private lazy val r: r = (x - y).divMod(y)
        private type r = x# -[y]#divMod[y]
    }
}
