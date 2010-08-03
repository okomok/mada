

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Find[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
    type self = Find[xs, f]

    private lazy val ys: ys = new DropWhile(xs, f.not)
    private type ys         =     DropWhile[xs, f#not]

    override  def apply: apply = `if`(ys.isEmpty, Const0(None), new Else).apply.asInstanceOfOption.asInstanceOf[apply]
    override type apply        = `if`[ys#isEmpty, Const0[None],     Else]#apply#asInstanceOfOption

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = Some(ys.head)
        override type apply        = Some[ys#head]
    }
}
