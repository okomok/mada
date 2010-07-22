

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class ConsMultiply {
     def apply[x <: Boolean, xs <: Dense, ys <: Dense](x: x, xs: xs, ys: ys): apply[x, xs, ys] =
        `if`(x, Then(x, xs, ys), Else(x, xs, ys)).apply.asInstanceOfNatDense
    type apply[x <: Boolean, xs <: Dense, ys <: Dense] =
        `if`[x, Then[x, xs, ys], Else[x, xs, ys]]#apply#asInstanceOfNatDense

    case class Then[x <: Boolean, xs <: Dense, ys <: Dense](x: x, xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Then[x, xs, ys]
        override  def apply: apply = (ys + (xs ** ys).shiftLeft).asInstanceOf[apply]
        override type apply = ys# +[xs# **[ys]#shiftLeft]
    }

    case class Else[x <: Boolean, xs <: Dense, ys <: Dense](x: x, xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[x, xs, ys]
        override  def apply: apply = (xs ** ys).shiftLeft
        override type apply = xs# **[ys]# shiftLeft
    }
}