

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class ReduceLeft[xs <: List, f <: Function2](xs: xs, f: f) extends Function0 {
    type self = ReduceLeft[xs, f]

    override  def apply: apply = `if`(xs.isEmpty, throw0(new UnsupportedOperationException("dual.List.reduceLeft for empty list")), new Else).apply
    override type apply        = `if`[xs#isEmpty, throw0[_], Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new FoldLeft(xs.tail, xs.head, f).apply.asInstanceOf[apply]
        override type apply        =     FoldLeft[xs#tail, xs#head, f]#apply
    }
}

final class ReduceRight[xs <: List, f <: Function2](xs: xs, f: f) extends Function0 {
    type self = ReduceRight[xs, f]

    override  def apply: apply = `if`(xs.isEmpty, throw0(new UnsupportedOperationException("dual.List.reduceRight empty list")), new Else).apply
    override type apply        = `if`[xs#isEmpty, throw0[_], Else]#apply

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new FoldRight(xs.tail, xs.head, f).apply.asInstanceOf[apply]
        override type apply        =     FoldRight[xs#tail, xs#head, f]#apply
    }
}
