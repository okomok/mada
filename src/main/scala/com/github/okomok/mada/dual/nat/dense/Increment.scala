

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class ConsIncrement {
     def apply[x <: Boolean, xs <: Dense](x: x, xs: xs): apply[x, xs] =
        `if`(x, Then(x, xs), Else(x, xs)).apply.asInstanceOfNatDense
    type apply[x <: Boolean, xs <: Dense] =
        `if`[x, Then[x, xs], Else[x, xs]]#apply#asInstanceOfNatDense

    // (`true` :: xs).increment
    case class Then[x <: Boolean, xs <: Dense](x: x, xs: xs) extends Function0 {
        override  val self = this
        override type self = Then[x, xs]
        override  def apply: apply = Cons(`false`, xs.increment)
        override type apply = Cons[`false`, xs#increment]
    }

    // (`false` :: xs).increment
    case class Else[x <: Boolean, xs <: Dense](x: x, xs: xs) extends Function0 {
        override  val self = this
        override type self = Else[x, xs]
        override  def apply: apply = Cons(`true`, xs)
        override type apply = Cons[`true`, xs]
    }
}
