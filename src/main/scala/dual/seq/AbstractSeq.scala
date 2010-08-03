

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


trait AbstractSeq extends Seq {
    final override  def head: head = begin.deref
    final override type head       = begin#deref

    final override  def tail: tail  = new Bind(self.begin.next)
    private type _tail[self <: Seq] =     Bind[self#begin#next]
    final override type tail = _tail[self]

    final override  def addFirst[e <: Any](e: e): addFirst[e] = throw new Error
    final override type addFirst[e <: Any] <: Seq

    final override  def addLast[e <: Any](e: e): addLast[e] = throw new Error
    final override type addLast[e <: Any] <: Seq

    final override  def clear: clear = new Bind(iterator.End)
    private type _clear[self <: Seq] =     Bind[iterator.End]
    final override type clear = _clear[self]

    final override  def foreach[f <: Function1](f: f): foreach[f] = new iterator.Foreach(self.begin, f).apply
    private type _foreach[self <: Seq, f <: Function1]            =     iterator.Foreach[self#begin, f]#apply
    final override type foreach[f <: Function1] = _foreach[self, f]

    final override  def isEmpty: isEmpty = begin.isEnd
    final override type isEmpty          = begin#isEnd

    final override  def nonEmpty: nonEmpty = isEmpty.not
    final override type nonEmpty           = isEmpty#not

    final override lazy val length: length = new iterator.Length(self.begin).apply
    private type _length[self <: Seq]      =     iterator.Length[self#begin]#apply
    final override type length = _length[self]

    final override  def ++[that <: Seq](that: that): append[that] = new Bind(new iterator.Append(self.begin, that.begin))
    private type _append[self <: Seq,  that <: Seq]               =     Bind[    iterator.Append[self#begin, that#begin]]
    final override type ++[that <: Seq] = _append[self, that]

    final override  def map[f <: Function1](f: f): map[f] = new Bind(new iterator.Map(self.begin, f))
    private type _map[self <: Seq, f <: Function1]        =     Bind[    iterator.Map[self#begin, f]]
    final override type map[f <: Function1] = _map[self, f]

    final override  def flatMap[f <: Function1](f: f): flatMap[f] = self.map(f).flatten
    private type _flatMap[self <: Seq, f <: Function1]            = self#map[f]#flatten
    final override type flatMap[f <: Function1] = _flatMap[self, f]

    final override  def flatten: flatten = new Bind(iterator.Flatten(self.begin))
    private type _flatten[self <: Seq]   =     Bind[iterator.Flatten[self#begin]]
    final override type flatten <: Seq = _flatten[self]

    final override  def filter[f <: Function1](f: f): filter[f] = new Bind(new iterator.Filter(self.begin, f))
    private type _filter[self <: Seq, f <: Function1]           =     Bind[    iterator.Filter[self#begin, f]]
    final override type filter[f <: Function1] = _filter[self, f]

    final override  def partition[f <: Function1](f: f): partition[f] = throw new Error
    final override type partition[f <: Function1] <: Product2

    final override  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final override type forall[f <: Function1]                  = exists[f#not]#not

    final override  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final override type exists[f <: Function1]                  = find[f]#nonEmpty

    final override  def count[f <: Function1](f: f): count[f] = new iterator.Count(self.begin, f).apply
    private type _count[self <: Seq, f <: Function1]         =      iterator.Count[self#begin, f]#apply
    final override type count[f <: Function1] = _count[self, f]

    final override  def find[f <: Function1](f: f): find[f] = new iterator.Find(self.begin, f).apply
    private type _find[self <: Seq, f <: Function1]         =     iterator.Find[self#begin, f]#apply
    final override type find[f <: Function1] = _find[self, f]

    final override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = new iterator.FoldLeft(self.begin, z, f).apply
    private type _foldLeft[self <: Seq, z <: Any, f <: Function2]                      =     iterator.FoldLeft[self#begin, z, f]#apply
    final override type foldLeft[z <: Any, f <: Function2] = _foldLeft[self, z, f]

    final override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = new iterator.FoldRight(self.begin, z, f).apply
    private type _foldRight[self <: Seq, z <: Any, f <: Function2]                      =     iterator.FoldRight[self#begin, z, f]#apply
    final override type foldRight[z <: Any, f <: Function2] = _foldRight[self, z, f]

    final override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = new ReduceLeft().apply(begin, f)
    final override type reduceLeft[f <: Function2] = ReduceLeft#apply[begin, f]

    final override  def reduceRight[f <: Function2](f: f): reduceRight[f] = new ReduceRight().apply(begin, f)
    final override type reduceRight[f <: Function2] = ReduceRight#apply[begin, f]

    final override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = throw new Error
    final override type scanLeft[z <: Any, f <: Function2] <: Seq

    final override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = throw new Error
    final override type scanRight[z <: Any, f <: Function2] <: Seq

    final override  def last: last = throw new Error
    final override type last <: Any

    final override  def init: init = throw new Error
    final override type init <: Seq

    final override  def take[n <: Nat](n: n): take[n] = new Bind(new iterator.Take(self.begin, n))
    private type _take[self <: Seq, n <: Nat]         =     Bind[    iterator.Take[self#begin, n]]
    final override type take[n <: Nat] = _take[self, n]

    final override  def drop[n <: Nat](n: n): drop[n] = new Bind(new iterator.Drop(self.begin, n))
    private type _drop[self <: Seq, n <: Nat]         =     Bind[    iterator.Drop[self#begin, n]]
    final override type drop[n <: Nat] = _drop[self, n]

    final override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = self.take(n).drop(m)
    private type _slice[self <: Seq, n <: Nat, m <: Nat]                   = self#take[n]#drop[m]
    final override type slice[n <: Nat, m <: Nat] = _slice[self, n, m]

    final override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new Bind(new iterator.TakeWhile(self.begin, f))
    private type _takeWhile[self <: Seq, f <: Function1]              =     Bind[    iterator.TakeWhile[self#begin, f]]
    final override type takeWhile[f <: Function1] = _takeWhile[self, f]

    final override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new Bind(new iterator.DropWhile(self.begin, f))
    private type _dropWhile[self <: Seq, f <: Function1]              =     Bind[    iterator.DropWhile[self#begin, f]]
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
