

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class FoldLeft[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
    type self = FoldLeft[xs, z, f]

    override  def apply: apply = `if`(xs.isEmpty, Const0(z), new Else).apply
    override type apply        = `if`[xs#isEmpty, Const0[z],     Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new FoldLeft(xs.tail, f.apply(z, xs.head), f).apply.asInstanceOf[apply]
        override type apply        =     FoldLeft[xs#tail, f#apply[z, xs#head], f]#apply
    }
}

final class FoldRight[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
    type self = FoldRight[xs, z, f]

    override  def apply: apply = `if`(xs.isEmpty, Const0(z), new Else).apply
    override type apply        = `if`[xs#isEmpty, Const0[z],     Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = f.apply(xs.head, new FoldRight(xs.tail, z, f).apply).asInstanceOf[apply]
        override type apply        = f#apply[xs#head,     FoldRight[xs#tail, z, f]#apply]
    }
}
