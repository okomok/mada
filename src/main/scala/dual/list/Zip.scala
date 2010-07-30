

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsZip {
     def apply[xs <: List, ys <: Seq](xs: xs, ys: ys): apply[xs, ys] =
        `if`(ys.isEmpty, Const0(Nil), Else(xs, ys)).apply.asInstanceOfList
    type apply[xs <: List, ys <: Seq] =
        `if`[ys#isEmpty, Const0[Nil], Else[xs, ys]]#apply#asInstanceOfList

    case class Else[xs <: List, ys <: Seq](xs: xs, ys: ys) extends Function0 {
        type self = Else[xs, ys]
        override  def apply: apply = Cons(Tuple2(xs.head, ys.head), xs.tail.zip(ys.tail))
        override type apply        = Cons[Tuple2[xs#head, ys#head], xs#tail#zip[ys#tail]]
    }
}
