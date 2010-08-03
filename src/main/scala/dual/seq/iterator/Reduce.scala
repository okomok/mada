

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class ReduceLeft[it <: Iterator, f <: Function2](it: it, f: f) extends Function0 {
    type self = ReduceLeft[it, f]

    override  def apply: apply = `if`(it.isEnd, Throw0(new UnsupportedOperationException("dual.Seq.reduceLeft for empty seq")), new Else).apply
    override type apply        = `if`[it#isEnd, Throw0, Else]#apply

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = new FoldLeft(it.next, it.deref, f)
        override type apply        =     FoldLeft[it#next, it#deref, f]
    }
}

final class ReduceRight {
     def apply[it <: Seq, f <: Function2](it: it, f: f): apply[it, f] =
        `if`(it.isEnd, Throw0(new UnsupportedOperationException("dual.Seq.reduceRight for empty seq")), Else(it, f)).apply
    type apply[it <: Seq, f <: Function2] =
        `if`[it#isEnd, Throw0, Else[it, f]]#apply

    case class Else[it <: Seq, f <: Function2](it: it, f: f) extends Function0 {
        type self = Else[it, f]
        override  def apply: apply = it.tail.foldRight(it.head, f)
        override type apply        = it#tail#foldRight[it#head, f]
    }
