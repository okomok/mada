

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package views


private[mada] final class Foreach {
     def apply[it <: Iterator, f <: Function1](it: it, f: f): apply[it, f] =
        `if`(it.isEnd, Const0(Unit), Else(it, f)).apply.asInstanceOfUnit
    type apply[it <: Iterator, f <: Function1] = Unit

    case class Else[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
        type self = Else[it, f]
        override  def apply: apply = { f.apply(it.deref); View(it.next).foreach(f) }
        override type apply = Unit
    }
}
