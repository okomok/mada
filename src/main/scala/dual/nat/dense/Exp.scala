

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class NilExp {
     def apply[x <: Dense, n <: Nat](x: x, n: n): apply[x, n] = `if`(n.isZero, const0(_1), const0(x)).apply.asInstanceOfNatDense
    type apply[x <: Dense, n <: Nat] = `if`[n#isZero, const0[_1], const0[x]]#apply#asInstanceOfNatDense
}


private[mada] final class ConsExp {
     def apply[x <: Dense, n <: Nat](x: x, n: n): apply[x, n] = n.toPeano.foldRight(_1, Step(x)).asInstanceOfNatDense
    type apply[x <: Dense, n <: Nat] = n#toPeano#foldRight[_1, Step[x]]#asInstanceOfNatDense

    case class Step[x <: Dense](x: x) extends Function2 {
        type self = Step[x]
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = x ** b.asInstanceOfNatDense
        override type apply[a <: Any, b <: Any] = x# **[b#asInstanceOfNatDense]
    }
}
