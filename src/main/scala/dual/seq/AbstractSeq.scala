

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


trait AbstractSeq extends Seq {
    final override  def ::[e <: Any](e: e): ::[e] = new AddFirst(self, e)
    private type _addFirst[self <: Seq, e <: Any] =     AddFirst[self, e]
    final override type ::[e <: Any] = _addFirst[self, e]

    final override  def clear: clear = Empty
    final override type clear        = Empty

    final override  def foreach[f <: Function1](f: f): foreach[f] = new Foreach(self, f).apply
    private type _foreach[self <: Seq, f <: Function1]            =     Foreach[self, f]#apply
    final override type foreach[f <: Function1] = _foreach[self, f]

    final override  def nonEmpty: nonEmpty = isEmpty.not
    final override type nonEmpty           = isEmpty#not

    final override  def size: size = unsupported("Seq.size")
    final override type size       = unsupported[_]

    final override lazy val length: length = new Length(self).apply
    private type _length[self <: Seq]      =     Length[self]#apply
    final override type length = _length[self]

    final override  def ++[that <: Seq](that: that): append[that] = new Append(self, that)
    private type _append[self <: Seq,  that <: Seq]               =     Append[self, that]]
    final override type ++[that <: Seq] = _append[self, that]

    final override  def map[f <: Function1](f: f): map[f] = new Map(self, f)
    private type _map[self <: Seq, f <: Function1]        =     Map[self, f]
    final override type map[f <: Function1] = _map[self, f]

    final override  def flatMap[f <: Function1](f: f): flatMap[f] = self.map(f).flatten
    private type _flatMap[self <: Seq, f <: Function1]            = self#map[f]#flatten
    final override type flatMap[f <: Function1] = _flatMap[self, f]

    final override  def flatten: flatten = Flatten(self)
    private type _flatten[self <: Seq]   =     Bind[Flatten[self]]
    final override type flatten <: Seq = _flatten[self]

    final override  def filter[f <: Function1](f: f): filter[f] = new Filter(self, f)
    private type _filter[self <: Seq, f <: Function1]           =     Filter[self, f]
    final override type filter[f <: Function1] = _filter[self, f]

    final override  def partition[f <: Function1](f: f): partition[f] = throw new Error
    final override type partition[f <: Function1] <: Product2

    final override  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final override type forall[f <: Function1]                  = exists[f#not]#not

    final override  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final override type exists[f <: Function1]                  = find[f]#nonEmpty

    final override  def count[f <: Function1](f: f): count[f] = new Count(self, f).apply
    private type _count[self <: Seq, f <: Function1]         =      Count[self, f]#apply
    final override type count[f <: Function1] = _count[self, f]

    final override  def find[f <: Function1](f: f): find[f] = new Find(self, f).apply
    private type _find[self <: Seq, f <: Function1]         =     Find[self, f]#apply
    final override type find[f <: Function1] = _find[self, f]

    final override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = new FoldLeft(self, z, f).apply
    private type _foldLeft[self <: Seq, z <: Any, f <: Function2]                      =     FoldLeft[self, z, f]#apply
    final override type foldLeft[z <: Any, f <: Function2] = _foldLeft[self, z, f]

    final override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = new FoldRight(self, z, f).apply
    private type _foldRight[self <: Seq, z <: Any, f <: Function2]                      =     FoldRight[self, z, f]#apply
    final override type foldRight[z <: Any, f <: Function2] = _foldRight[self, z, f]

    final override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = new ReduceLeft().apply, f)
    final override type reduceLeft[f <: Function2] = ReduceLeft#apply, f]

    final override  def reduceRight[f <: Function2](f: f): reduceRight[f] = new ReduceRight().apply, f)
    final override type reduceRight[f <: Function2] = ReduceRight#apply, f]

    final override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = throw new Error
    final override type scanLeft[z <: Any, f <: Function2] <: Seq

    final override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = throw new Error
    final override type scanRight[z <: Any, f <: Function2] <: Seq

    final override  def last: last = throw new Error
    final override type last <: Any

    final override  def init: init = throw new Error
    final override type init <: Seq

    final override  def take[n <: Nat](n: n): take[n] = new Take(self, n)
    private type _take[self <: Seq, n <: Nat]         =     Take[self, n]
    final override type take[n <: Nat] = _take[self, n]

    final override  def drop[n <: Nat](n: n): drop[n] = new Drop(self, n)
    private type _drop[self <: Seq, n <: Nat]         =     Drop[self, n]
    final override type drop[n <: Nat] = _drop[self, n]

    final override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = self.take(n).drop(m)
    private type _slice[self <: Seq, n <: Nat, m <: Nat]                   = self#take[n]#drop[m]
    final override type slice[n <: Nat, m <: Nat] = _slice[self, n, m]

    final override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new TakeWhile(self, f)
    private type _takeWhile[self <: Seq, f <: Function1]              =     TakeWhile[self, f]
    final override type takeWhile[f <: Function1] = _takeWhile[self, f]

    final override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new DropWhile(self, f)
    private type _dropWhile[self <: Seq, f <: Function1]              =     DropWhile[self, f]
    final override type dropWhile[f <: Function1] = _dropWhile[self, f]

    final override  def span[f <: Function1](f: f): span[f] = throw new Error
    final override type span[f <: Function1] <: Product2

    final override  def splitAt[n <: Nat](n: n): splitAt[n] = throw new Error
    final override type splitAt[n <: Nat] <: Product2

    final override  def equivTo[that <: Seq, e <: Equiv](that: that, e: e): equivTo[that, e] = throw new Error
    final override type equivTo[that <: Seq, e <: Equiv] <: Boolean

    final override  def reverse: reverse = unsupported("Bind.reverse")
    final override type reverse          = unsupported[_]

    final override  def zip[that <: Seq](that: that): zip[that] = throw new Error
    final override type zip[that <: Seq] <: Seq

    final override  def unzip: unzip = throw new Error
    final override type unzip <: Product2

    final override  def toList: toList = throw new Error
    final override type toList <: List

    final override  def fromSuper[that <: Seq](that: that): fromSuper[that] = throw new Error
    final override type fromSuper[that <: Seq] <: Seq
}
