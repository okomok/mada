

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Foreach {
     def apply[xs <: Seq, f <: Function1](xs: xs, f: f): apply[xs, f] =
        `if`(xs.isEmpty, Const0(Unit), Else(xs, f)).apply.asInstanceOfUnit
    type apply[xs <: Seq, f <: Function1] = Unit

    case class Else[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = { f.apply(xs.head); xs.tail.foreach(f) }
        override type apply = Unit
    }
}
