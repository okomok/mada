

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class ReduceLeft {
     def apply[xs <: Seq, f <: Function2](xs: xs, f: f): apply[xs, f] =
        `if`(xs.isEmpty, Throw0(new UnsupportedOperationException("dual.seq.reduceLeft for empty seq")), Else(xs, f)).apply
    type apply[xs <: Seq, f <: Function2] =
        `if`[xs#isEmpty, Throw0, Else[xs, f]]#apply

    case class Else[xs <: Seq, f <: Function2](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = xs.tail.foldLeft(xs.head, f)
        override type apply        = xs#tail#foldLeft[xs#head, f]
    }
}

private[mada] final class ReduceRight {
     def apply[xs <: Seq, f <: Function2](xs: xs, f: f): apply[xs, f] =
        `if`(xs.isEmpty, Throw0(new UnsupportedOperationException("dual.seq.reduceRight for empty seq")), Else(xs, f)).apply
    type apply[xs <: Seq, f <: Function2] =
        `if`[xs#isEmpty, Throw0, Else[xs, f]]#apply

    case class Else[xs <: Seq, f <: Function2](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = xs.tail.foldRight(xs.head, f)
        override type apply        = xs#tail#foldRight[xs#head, f]
    }
}
