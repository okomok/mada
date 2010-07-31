

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class FoldLeft {
     def apply[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] =
        `if`(xs.isEmpty, Const0(z), Else(xs, z, f)).apply
    type apply[xs <: Seq, z <: Any, f <: Function2] =
        `if`[xs#isEmpty, Const0[z], Else[xs, z, f]]#apply

    case class Else[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = xs.tail.foldLeft(f.apply(z, xs.head), f)
        override type apply        = xs#tail#foldLeft[f#apply[z, xs#head], f]
    }
}

private[mada] final class FoldRight {
     def apply[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] =
        `if`(xs.isEmpty, Const0(z), Else(xs, z, f)).apply
    type apply[xs <: Seq, z <: Any, f <: Function2] =
        `if`[xs#isEmpty, Const0[z], Else[xs, z, f]]#apply

    case class Else[xs <: Seq, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = f.apply(xs.head, xs.tail.foldRight(z, f))
        override type apply        = f#apply[xs#head, xs#tail#foldRight[z, f]]
    }
}
