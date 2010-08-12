

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object FoldLeft {
     def apply[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] =
        `if`(xs.isEmpty, const0(z), Else(xs, z, f)).apply
    type apply[xs <: List, z <: Any, f <: Function2] =
        `if`[xs#isEmpty, const0[z], Else[xs, z, f]]#apply

    case class Else[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = FoldLeft.apply(xs.tail, f.apply(z, xs.head), f).asInstanceOf[apply]
        override type apply        = FoldLeft.apply[xs#tail, f#apply[z, xs#head], f]
    }
}


private[dual]
object FoldRight {
     def apply[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] =
        `if`(xs.isEmpty, const0(z), Else(xs, z, f)).apply
    type apply[xs <: List, z <: Any, f <: Function2] =
        `if`[xs#isEmpty, const0[z], Else[xs, z, f]]#apply

    case class Else[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
        type self = Else[xs, z, f]
        override  def apply: apply = f.apply(xs.head, FoldRight.apply(xs.tail, z, f)).asInstanceOf[apply]
        override type apply        = f#apply[xs#head, FoldRight.apply[xs#tail, z, f]]
    }
}