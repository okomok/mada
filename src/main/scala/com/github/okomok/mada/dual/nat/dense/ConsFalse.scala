

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class ConsFalse[xs <: Dense](xs: xs) extends Function0 {
    override  def self = this
    override type self = ConsFalse[xs]
    override  def apply: apply = xs.isEmpty.`if`(always0(xs), always0(Cons(`false`, xs))).apply
    override type apply = xs#isEmpty#`if`[always0[xs], always0[Cons[`false`, xs]]]#apply
}
