

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Foreach[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
    type self = Foreach[xs, f]
    override  def apply: apply = `if`(xs.isEmpty, Const0(Unit), new Else).apply.asInstanceOfUnit
    override type apply = Unit

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = { f.apply(xs.head); new Foreach(xs.tail, f).apply }
        override type apply = Unit
    }
}
