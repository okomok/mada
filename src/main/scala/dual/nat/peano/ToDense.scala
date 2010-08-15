

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[dual]
object ToDense {
     def apply[x <: Peano](x: x): apply[x] = `if`(x.isZero, const0(dense.Nil), Else(x)).apply.asNatDense
    type apply[x <: Peano]                 = `if`[x#isZero, const0[dense.Nil], Else[x]]#apply#asNatDense

    case class Else[x <: Peano](x: x) extends Function0 {
         type self = Else[x]
         override  def apply: apply = dense.Cons(x.isOdd, Div2.apply(x).asNatDense) // `ConsFalse` is redundant.
         override type apply        = dense.Cons[x#isOdd, Div2.apply[x]#asNatDense]
     }
}


private[dual]
object Div2 {
     def apply[x <: Peano](x: x): apply[x] = `if`(x.lt(_2), const0(Zero), Else(x)).apply.asNatPeano
    type apply[x <: Peano]                 = `if`[x#lt[_2], const0[Zero], Else[x]]#apply#asNatPeano

    case class Else[x <: Peano](x: x) extends Function0 {
        type self = Else[x]
        override  def apply: apply = Div2.apply(x.decrement.decrement).increment.asInstanceOf[apply]
        override type apply        = Div2.apply[x#decrement#decrement]#increment
    }
}
