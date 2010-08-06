

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
final class SuccEq {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] =
        `if`(y.isZero, const0(`false`), Else(x, y)).apply.asInstanceOfBoolean
    type apply[x <: Peano, y <: Peano] =
        `if`[y#isZero, const0[`false`], Else[x, y]]#apply#asInstanceOfBoolean

    case class Else[x <: Peano, y <: Peano](x: x, y: y) extends Function0 {
         type self = Else[x, y]
         override  def apply: apply = x.decrement === y.decrement
         override type apply = x#decrement# ===[y#decrement]
     }
}

private[dual]
final class SuccLtEq {
     def apply[x <: Peano, y <: Peano](x: x, y: y): apply[x, y] =
        `if`(y.isZero, const0(`false`), Else(x, y)).apply.asInstanceOfBoolean
    type apply[x <: Peano, y <: Peano] =
        `if`[y#isZero, const0[`false`], Else[x, y]]#apply#asInstanceOfBoolean

    case class Else[x <: Peano, y <: Peano](x: x, y: y) extends Function0 {
         type self = Else[x, y]
         override  def apply: apply = x.decrement <= y.decrement
         override type apply = x#decrement# <=[y#decrement]
     }
}
