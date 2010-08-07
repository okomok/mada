

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Foreach {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] = `if`(xs.isEmpty, const0(Unit), new Else(xs, f)).apply.asInstanceOfUnit
    type apply[xs <: List, f <: Function1]                             = Unit

    class Else[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = { f.apply(xs.head); Foreach.apply(xs.tail, f) }
        override type apply = Unit
    }
}
