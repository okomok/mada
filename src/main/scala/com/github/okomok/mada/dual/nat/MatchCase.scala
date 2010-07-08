

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class MatchCaseCons[xs <: Nat, ys <: Nat, tt <: Function0, tf <: Function0, ft <: Function0, ff <: Function0](xs: xs, ys: ys, tt: tt, tf: tf, ft: ft, ff: ff) extends Function0 {
    override  def self = this
    override type self = MatchCaseCons[xs, ys, tt, tf, ft, ff]
    override  def apply: apply = xs.head.`if`(ys.head.`if`(tt, tf), ys.head.`if`(ft, ff)).apply.asInstanceOf[apply]
    override type apply = xs#head#`if`[ys#head#`if`[tt, tf], ys#head#`if`[ft, ff]]#apply
}
