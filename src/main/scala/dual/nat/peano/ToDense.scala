

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final class ToDense {
     def apply[x <: Peano](x: x): apply[x] = `if`(x.isZero, const0(dense.Nil), Else(x)).apply.asInstanceOfNatDense
    type apply[x <: Peano] = `if`[x#isZero, const0[dense.Nil], Else[x]]#apply#asInstanceOfNatDense

    case class Else[x <: Peano](x: x) extends Function0 {
         override  val self = this
         override type self = Else[x]
         override  def apply: apply = dense.Cons(x.isOdd, new Div2().apply(x).toDense) // `ConsFalse` is redundant.
         override type apply = dense.Cons[x#isOdd, Div2#apply[x]#toDense]
     }
}


private[mada] final class Div2  {
      def apply[x <: Peano](x: x): apply[x] = `if`(x < _2, const0(Zero), Else(x)).apply.asInstanceOfNatPeano
     type apply[x <: Peano] = `if`[x# <[_2], const0[Zero], Else[x]]#apply#asInstanceOfNatPeano

     case class Else[x <: Peano](x: x) extends Function0 {
         override  val self = this
         override type self = Else[x]
         override  def apply: apply = new Div2().apply(x.decrement.decrement).increment.asInstanceOf[apply]
         override type apply = Div2#apply[x#decrement#decrement]#increment
     }
}
