

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class ConsDecrement {
     def apply[x <: Boolean, xs <: Dense](x: x, xs: xs): apply[x, xs] = `if`(xs.isEmpty, always0(xs), `if`(x, new Then(x, xs), new Else(x, xs))).apply.asInstanceOfNatDense.asInstanceOf[apply[x, xs]]
    type apply[x <: Boolean, xs <: Dense] = `if`[xs#isEmpty, always0[xs], `if`[x, Then[x, xs], Else[x, xs]]]#apply#asInstanceOfNatDense

    // (`true` :: xs).decrement
    class Then[x <: Boolean, xs <: Dense](x: x, xs: xs) extends Function0 {
        override  val self = this
        override type self = Then[x, xs]
        override  def apply: apply = Cons(`false`, xs)
        override type apply = Cons[`false`, xs]
    }

    // (`false` :: xs).decrement
    class Else[x <: Boolean, xs <: Dense](x: x, xs: xs) extends Function0 {
        override  val self = this
        override type self = Else[x, xs]
        override  def apply: apply = Cons(`true`, xs.decrement)
        override type apply = Cons[`true`, xs#decrement]
    }
}
