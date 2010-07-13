

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class Match[xs <: Dense, ys <: Dense, nn <: Function0, nc <: Function0, cn <: Function0, cc <: Function0](xs: xs, ys: ys, nn: nn, nc: nc, cn: cn, cc: cc) extends Function0 {
    override  val self = this
    override type self = Match[xs, ys, nn, nc, cn, cc]
    override  def apply: apply = `if`(xs.isEmpty, `if`(ys.isEmpty, nn, nc), `if`(ys.isEmpty, cn, cc)).apply.asInstanceOf[apply]
    override type apply = `if`[xs#isEmpty, `if`[ys#isEmpty, nn, nc], `if`[ys#isEmpty, cn, cc]]#apply
}

private[mada] final case class ConsMatch[xs <: Dense, ys <: Dense, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0](xs: xs, ys: ys, tt: tt, tf: tf, ft: ft, ff: ff) extends Function0 {
    override  val self = this
    override type self = ConsMatch[xs, ys, tt, tf, ft, ff]
    override  def apply: apply = `if`(xs.head, `if`(ys.head, tt, tf), `if`(ys.head, ft, ff)).apply.asInstanceOf[apply]
    override type apply = `if`[xs#head, `if`[ys#head, tt, tf], `if`[ys#head, ft, ff]]#apply
}