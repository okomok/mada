

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Find[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
    type self = Find[it, f]

    private lazy val jt: jt = new DropWhile(it, f.not)
    private type jt         =     DropWhile[it, f#not]

    override  def apply: apply = `if`(jt.isEnd, Const0(None), new Else).apply.asInstanceOfOption
    override type apply        = `if`[jt#isEnd, Const0[None],     Else]#apply#asInstanceOfOption

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = jt.deref
        override type apply        = jt#deref
    }
}
