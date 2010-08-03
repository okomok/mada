

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Foreach[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
    type self = Foreach[it, f]
    override  def apply: apply = `if`(it.isEnd, Const0(Unit), new Else).apply.asInstanceOfUnit
    override type apply = Unit

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = { f.apply(it.deref); new Foreach(it.next, f).apply }
        override type apply = Unit
    }
}
