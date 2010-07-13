

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class DecrementCons[x <: Boolean, xs <: Dense](x: x, xs: xs) {
     def apply: apply = `if`(xs.isEmpty, always0(xs), `if`(x, new Then, new Else)).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = `if`[xs#isEmpty, always0[xs], `if`[x, Then, Else]]#apply#asInstanceOfNatDense

    // (`true` :: xs).decrement
    class Then extends Function0 {
        override  val self = this
        override type self = Then
        override  def apply: apply = Cons(`false`, xs)
        override type apply = Cons[`false`, xs]
    }

    // (`false` :: xs).decrement
    class Else extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = Cons(`true`, xs.decrement)
        override type apply = Cons[`true`, xs#decrement]
    }
}
