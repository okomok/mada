

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class ReduceLeft[xs <: Seq, f <: Function2](xs: xs, f: f) extends Function0 {
    type self = ReduceLeft[xs, f]

    override  def apply: apply = `if`(xs.isEmpty, Throw0(new UnsupportedOperationException("dual.Seq.reduceLeft for empty seq")), new Else).apply
    override type apply        = `if`[xs#isEmpty, Throw0, Else]#apply

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = new FoldLeft(xs.tail, xs.head, f)
        override type apply        =     FoldLeft[xs#tail, xs#head, f]
    }
}

final class ReduceRight {
     def apply[xs <: Seq, f <: Function2](xs: xs, f: f): apply[xs, f] =
        `if`(xs.isEmpty, Throw0(new UnsupportedOperationException("dual.Seq.reduceRight for empty seq")), Else(xs, f)).apply
    type apply[xs <: Seq, f <: Function2] =
        `if`[xs#isEmpty, Throw0, Else[xs, f]]#apply

    case class Else[xs <: Seq, f <: Function2](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = xs.tail.foldRight(xs.head, f)
        override type apply        = xs#tail#foldRight[xs#head, f]
    }
