

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class FoldLeft[it <: Iterator, z <: Any, f <: Function2](it: it, z: z, f: f) extends Function0 {
    type self = FoldLeft[it, z, f]

    override  def apply: apply = `if`(it.isEnd, Const0(z), new Else).apply
    override type apply        = `if`[it#isEnd, Const0[z],     Else]#apply

    private class Else extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = new FoldLeft(it.next, f.apply(z, it.deref), f).apply
        override type apply        =     FoldLeft[it#next, f#apply[z, it#deref], f]#apply
    }
}

final class FoldRight[it <: Iterator, z <: Any, f <: Function2](it: it, z: z, f: f) extends Function0 {
    type self = FoldRight[it, z, f]

    override  def apply: apply = `if`(it.isEnd, Const0(z), new Else).apply
    override type apply        = `if`[it#isEnd, Const0[z],     Else]#apply

    private class Else extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = f.apply(it.deref, new FoldRight(xs.next, z, f).apply)
        override type apply        = f#apply[it#deref,     FoldRight[xs#next, z, f]#apply]
    }
}
