

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


trait AbstractList extends List {
    final override  def ::[x <: Any](x: x): ::[x]  = new Cons(x, self)
    private type _cons[self <: List, x <: Any]     =     Cons[x, self]
    final override type ::[x <: Any] = _cons[self, x]

    final override  def clear: clear = Nil
    final override type clear        = Nil

    final override  def foreach[f <: Function1](f: f): foreach[f] = new Foreach(self, f).apply
    private type _foreach[self <: List, f <: Function1]           =     Foreach[self, f]#apply
    final override type foreach[f <: Function1] = _foreach[self, f]

    final override lazy val length: length = new Length(self).apply
    private type _length[self <: List]     =     Length[self]#apply
    final override type length = _length[self]

    final override  def ++[that <: List](that: that): ++[that] = new Append(self, that)
    private type _append[self <: List,  that <: List]          =     Append[self, that]
    final override type ++[that <: List] = _append[self, that]

    final override  def map[f <: Function1](f: f): map[f] = new Map(self, f)
    private type _map[self <: List, f <: Function1]       =     Map[self, f]
    final override type map[f <: Function1] = _map[self, f]

    final override  def flatMap[f <: Function1](f: f): flatMap[f] = self.map(f).flatten
    private type _flatMap[self <: List, f <: Function1]           = self#map[f]#flatten
    final override type flatMap[f <: Function1] = _flatMap[self, f]

    final override  def flatten: flatten = new Flatten(self)
    private type _flatten[self <: List]   =     Flatten[self]
    final override type flatten = _flatten[self]

    final override  def filter[f <: Function1](f: f): filter[f] = new Filter(self, f)
    private type _filter[self <: List, f <: Function1]          =     Filter[self, f]
    final override type filter[f <: Function1] = _filter[self, f]

    final override  def partition[f <: Function1](f: f): partition[f] = Tuple2(new Filter(self, f), new Filter(self, f.not))
    private type _partition[self <: List, f <: Function1]             = Tuple2[    Filter[self, f],     Filter[self, f#not]]
    final override type partition[f <: Function1]  = _partition[self, f]

    final override  def sort[o <: Ordering](o: o): sort[o] = new Sort(self, o)
    private type _sort[self <: List, o <: Ordering]        =     Sort[self, o]
    final override type sort[o <: Ordering] = _sort[self, o]

    final override  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final override type forall[f <: Function1]                  = exists[f#not]#not

    final override  def exists[f <: Function1](f: f): exists[f] = find(f).isEmpty.not
    final override type exists[f <: Function1]                  = find[f]#isEmpty#not

    final override  def count[f <: Function1](f: f): count[f] = new Count(self, f).apply
    private type _count[self <: List, f <: Function1]         =     Count[self, f]#apply
    final override type count[f <: Function1] = _count[self, f]

    final override  def find[f <: Function1](f: f): find[f] = new Find(self, f).apply
    private type _find[self <: List, f <: Function1]        =     Find[self, f]#apply
    final override type find[f <: Function1] = _find[self, f]

    final override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = new FoldLeft(self, z, f).apply
    private type _foldLeft[self <: List, z <: Any, f <: Function2]                     =     FoldLeft[self, z, f]#apply
    final override type foldLeft[z <: Any, f <: Function2] = _foldLeft[self, z, f]

    final override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = new FoldRight(self, z, f).apply
    private type _foldRight[self <: List, z <: Any, f <: Function2]                     =      FoldRight[self, z, f]#apply
    final override type foldRight[z <: Any, f <: Function2] = _foldRight[self, z, f]

    final override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = new ReduceLeft(self, f).apply
    private type _reduceLeft[self <: List, f <: Function2]              =     ReduceLeft[self, f]#apply
    final override type reduceLeft[f <: Function2] = _reduceLeft[self, f]

    final override  def reduceRight[f <: Function2](f: f): reduceRight[f] = new ReduceRight(self, f).apply
    private type _reduceRight[self <: List, f <: Function2]              =      ReduceRight[self, f]#apply
    final override type reduceRight[f <: Function2] = _reduceRight[self, f]

    final override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = new ScanLeft(self, z, f)
    private type _scanLeft[self <: List, z <: Any, f <: Function2]                     =     ScanLeft[self, z, f]
    final override type scanLeft[z <: Any, f <: Function2] = _scanLeft[self, z, f]

    final override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = new ScanRight(self, z, f)
    private type _scanRight[self <: List, z <: Any, f <: Function2]                     =      ScanRight[self, z, f]
    final override type scanRight[z <: Any, f <: Function2] = _scanRight[self, z, f]

    final override  def nth[n <: Nat](n: n): nth[n] = new Nth(self, n).apply
    private type _nth[self <: List, n <: Nat]       =     Nth[self, n]#apply
    final override type nth[n <: Nat] = _nth[self, n]

    final override lazy val last: last = new Last(self).apply
    private type _last[self <: List]   =     Last[self]#apply
    final override type last = _last[self]

    final override lazy val init: init = new Init(self)
    private type _init[self <: List]   =     Init[self]
    final override type init = _init[self]

    final override  def take[n <: Nat](n: n): take[n] = new Take(self, n)
    private type _take[self <: List, n <: Nat]        =     Take[self, n]
    final override type take[n <: Nat] = _take[self, n]

    final override  def drop[n <: Nat](n: n): drop[n] = new Drop(self, n)
    private type _drop[self <: List, n <: Nat]        =     Drop[self, n]
    final override type drop[n <: Nat] = _drop[self, n]

    final override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = self.take(m).drop(n)
    private type _slice[self <: List, n <: Nat, m <: Nat]                  = self#take[m]#drop[n]
    final override type slice[n <: Nat, m <: Nat] = _slice[self, n, m]

    final override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new TakeWhile(self, f)
    private type _takeWhile[self <: List, f <: Function1]             =     TakeWhile[self, f]
    final override type takeWhile[f <: Function1] = _takeWhile[self, f]

    final override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new DropWhile(self, f)
    private type _dropWhile[self <: List, f <: Function1]             =     DropWhile[self, f]
    final override type dropWhile[f <: Function1] = _dropWhile[self, f]

    final override  def span[f <: Function1](f: f): span[f] = Tuple2(new TakeWhile(self, f), new DropWhile(self, f))
    private type _span[self <: List, f <: Function1]        = Tuple2[    TakeWhile[self, f],     DropWhile[self, f]]
    final override type span[f <: Function1]  = _span[self, f]

    final override  def splitAt[n <: Nat](n: n): splitAt[n] = Tuple2(new Take(self, n), new Drop(self, n))
    private type _splitAt[self <: List, n <: Nat]           = Tuple2[    Take[self, n],     Drop[self, n]]
    final override type splitAt[n <: Nat]  = _splitAt[self, n]

    final override  def equivTo[that <: List, e <: Equiv](that: that, e: e): equivTo[that, e] = new EquivTo(self, that, e).apply
    private type _equivTo[self <: List, that <: List, e <: Equiv]                             =     EquivTo[self, that, e]#apply
    final override type equivTo[that <: List, e <: Equiv] = _equivTo[self, that, e]

    final override  def reverse: reverse = new ReverseAppend(self, Nil)
    private type _reverse[self <: List]  =     ReverseAppend[self, Nil]
    final override type reverse = _reverse[self]

    final override  def zip[that <: List](that: that): zip[that] = new Zip(self, that)
    private type _zip[self <: List,  that <: List]               =     Zip[self, that]
    final override type zip[that <: List] = _zip[self, that]

    final override  def unzip: unzip  = Tuple2(new Unzip1(self), new Unzip2(self))
    private type _unzip[self <: List] = Tuple2[    Unzip1[self],     Unzip2[self]]
    final override type unzip = _unzip[self]

    final override  def force: force  = new Force(self).apply
    private type _force[self <: List] =     Force[self]#apply
    final override type force = _force[self]
}
