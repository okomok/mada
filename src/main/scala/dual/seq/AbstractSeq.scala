

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


trait AbstractSeq extends Seq {
    final override  def foreach[f <: Function1](f: f): foreach[f] = new Foreach().apply(self, f)
    final override type foreach[f <: Function1] = Foreach#apply[self, f]

    final override  def nonEmpty: nonEmpty = isEmpty.not
    final override type nonEmpty           = isEmpty#not

    final override  def length: length = size
    final override type length = size

    final override  def flatMap[f <: Function1](f: f): flatMap[f] = map(f).flatten
    final override type flatMap[f <: Function1]                   = map[f]#flatten

    final override  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final override type forall[f <: Function1]                  = exists[f#not]#not

    final override  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final override type exists[f <: Function1]                  = find[f]#nonEmpty

    final override  def count[f <: Function1](f: f): count[f] = new Count().apply(self, f)
    final override type count[f <: Function1] = Count#apply[self, f]

    final override  def find[f <: Function1](f: f): find[f] = new Find().apply(self, f)
    final override type find[f <: Function1] = Find#apply[self, f]

    final override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = new FoldLeft().apply(self, z, f)
    final override type foldLeft[z <: Any, f <: Function2] = FoldLeft#apply[self, z, f]

    final override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = new FoldRight().apply(self, z, f)
    final override type foldRight[z <: Any, f <: Function2] = FoldRight#apply[self, z, f]

    final override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = new ReduceLeft().apply(self, f)
    final override type reduceLeft[f <: Function2] = ReduceLeft#apply[self, f]

    final override  def reduceRight[f <: Function2](f: f): reduceRight[f] = new ReduceRight().apply(self, f)
    final override type reduceRight[f <: Function2] = ReduceRight#apply[self, f]

    final override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = take(m).drop(n)
    final override type slice[n <: Nat, m <: Nat]                          = take[m]#drop[n]

    final override  def equivTo[that <: Seq, e <: Equiv](that: that, e: e): equivTo[that, e] = new EquivTo().apply(self, that, e)
    final override type equivTo[that <: Seq, e <: Equiv] = EquivTo#apply[self, that, e]
}
