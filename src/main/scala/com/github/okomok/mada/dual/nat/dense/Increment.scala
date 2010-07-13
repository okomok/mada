

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class IncrementCons[x <: Boolean, xs <: Dense](x: x, xs: xs) {
     def apply: apply = `if`(x, Then(), Else()).apply.asInstanceOfNatDense
    type apply = `if`[x, Then, Else]#apply#asInstanceOfNatDense

    // (`true` :: xs).increment
    final case class Then() extends Function0 {
        override  val self = this
        override type self = Then
        override  def apply: apply = Cons(`false`, xs.increment)
        override type apply = Cons[`false`, xs#increment]
    }

    // (`false` :: xs).increment
    final case class Else() extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = Cons(`true`, xs)
        override type apply = Cons[`true`, xs]
    }
}
