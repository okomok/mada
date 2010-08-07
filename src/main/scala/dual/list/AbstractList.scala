

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

    final override  def foreach[f <: Function1](f: f): foreach[f] = Foreach.apply(self, f)
    private type _foreach[self <: List, f <: Function1]           = Foreach.apply[self, f]
    final override type foreach[f <: Function1] = _foreach[self, f]

    final override lazy val length: length = Length.apply(self)
    private type _length[self <: List]     = Length.apply[self]
    final override type length = _length[self]

    final override  def append[that <: List](that: that): append[that] = Append.apply(self, that)
    private type _append[self <: List,  that <: List]                  = Append.apply[self, that]
    final override type append[that <: List] = _append[self, that]

    final override  def map[f <: Function1](f: f): map[f] = Map.apply(self, f)
    private type _map[self <: List, f <: Function1]       = Map.apply[self, f]
    final override type map[f <: Function1] = _map[self, f]

    final override  def flatMap[f <: Function1](f: f): flatMap[f] = self.map(f).flatten
    private type _flatMap[self <: List, f <: Function1]           = self#map[f]#flatten
    final override type flatMap[f <: Function1] = _flatMap[self, f]

    final override  def flatten: flatten = Flatten.apply(self)
    private type _flatten[self <: List]  = Flatten.apply[self]
    final override type flatten = _flatten[self]

    final override  def filter[f <: Function1](f: f): filter[f] = Filter.apply(self, f)
    private type _filter[self <: List, f <: Function1]          = Filter.apply[self, f]
    final override type filter[f <: Function1] = _filter[self, f]

    final override  def partition[f <: Function1](f: f): partition[f] = Tuple2(self.filter(f), self.filter(f.not))
    private type _partition[self <: List, f <: Function1]             = Tuple2[self#filter[f], self#filter[f#not]]
    final override type partition[f <: Function1]  = _partition[self, f]

    final override  def sort[o <: Ordering](o: o): sort[o] = Sort.apply(self, o)
    private type _sort[self <: List, o <: Ordering]        = Sort.apply[self, o]
    final override type sort[o <: Ordering] = _sort[self, o]

    final override  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final override type forall[f <: Function1]                  = exists[f#not]#not

    final override  def exists[f <: Function1](f: f): exists[f] = find(f).isEmpty.not
    final override type exists[f <: Function1]                  = find[f]#isEmpty#not

    final override  def count[f <: Function1](f: f): count[f] = self.filter(f).length
    private type _count[self <: List, f <: Function1]         = self#filter[f]#length
    final override type count[f <: Function1] = _count[self, f]

    final override  def find[f <: Function1](f: f): find[f] = Find.apply(self, f)
    private type _find[self <: List, f <: Function1]        = Find.apply[self, f]
    final override type find[f <: Function1] = _find[self, f]

    final override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = FoldLeft.apply(self, z, f)
    private type _foldLeft[self <: List, z <: Any, f <: Function2]                     = FoldLeft.apply[self, z, f]
    final override type foldLeft[z <: Any, f <: Function2] = _foldLeft[self, z, f]

    final override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = FoldRight.apply(self, z, f)
    private type _foldRight[self <: List, z <: Any, f <: Function2]                      = FoldRight.apply[self, z, f]
    final override type foldRight[z <: Any, f <: Function2] = _foldRight[self, z, f]

    final override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = self.tail.foldLeft(self.head, f)
    private type _reduceLeft[self <: List, f <: Function2]              = self#tail#foldLeft[self#head, f]
    final override type reduceLeft[f <: Function2] = _reduceLeft[self, f]

    final override  def reduceRight[f <: Function2](f: f): reduceRight[f] = self.tail.foldRight(self.head, f)
    private type _reduceRight[self <: List, f <: Function2]               = self#tail#foldRight[self#head, f]
    final override type reduceRight[f <: Function2] = _reduceRight[self, f]

    final override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = ScanLeft.apply(self, z, f)
    private type _scanLeft[self <: List, z <: Any, f <: Function2]                     = ScanLeft.apply[self, z, f]
    final override type scanLeft[z <: Any, f <: Function2] = _scanLeft[self, z, f]

    final override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = ScanRight.apply(self, z, f)
    private type _scanRight[self <: List, z <: Any, f <: Function2]                      = ScanRight.apply[self, z, f]
    final override type scanRight[z <: Any, f <: Function2] = _scanRight[self, z, f]

    final override  def nth[n <: Nat](n: n): nth[n] = Nth.apply(self, n)
    private type _nth[self <: List, n <: Nat]       = Nth.apply[self, n]
    final override type nth[n <: Nat] = _nth[self, n]

    final override lazy val last: last = Last.apply(self)
    private type _last[self <: List]   = Last.apply[self]
    final override type last = _last[self]

    final override lazy val init: init = Init.apply(self)
    private type _init[self <: List]   = Init.apply[self]
    final override type init = _init[self]

    final override  def take[n <: Nat](n: n): take[n] = Take.apply(self, n)
    private type _take[self <: List, n <: Nat]        = Take.apply[self, n]
    final override type take[n <: Nat] = _take[self, n]

    final override  def drop[n <: Nat](n: n): drop[n] = Drop.apply(self, n)
    private type _drop[self <: List, n <: Nat]        = Drop.apply[self, n]
    final override type drop[n <: Nat] = _drop[self, n]

    final override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = self.take(m).drop(n)
    private type _slice[self <: List, n <: Nat, m <: Nat]                  = self#take[m]#drop[n]
    final override type slice[n <: Nat, m <: Nat] = _slice[self, n, m]

    final override  def takeWhile[f <: Function1](f: f): takeWhile[f] = TakeWhile.apply(self, f)
    private type _takeWhile[self <: List, f <: Function1]             = TakeWhile.apply[self, f]
    final override type takeWhile[f <: Function1] = _takeWhile[self, f]

    final override  def dropWhile[f <: Function1](f: f): dropWhile[f] = DropWhile.apply(self, f)
    private type _dropWhile[self <: List, f <: Function1]             = DropWhile.apply[self, f]
    final override type dropWhile[f <: Function1] = _dropWhile[self, f]

    final override  def span[f <: Function1](f: f): span[f] = Tuple2(self.takeWhile(f), self.dropWhile(f))
    private type _span[self <: List, f <: Function1]        = Tuple2[self#takeWhile[f], self#dropWhile[f]]
    final override type span[f <: Function1]  = _span[self, f]

    final override  def splitAt[n <: Nat](n: n): splitAt[n] = Tuple2(self.take(n), self.drop(n))
    private type _splitAt[self <: List, n <: Nat]           = Tuple2[self#take[n], self#drop[n]]
    final override type splitAt[n <: Nat]  = _splitAt[self, n]

    final override  def equivTo[that <: List, e <: Equiv](that: that, e: e): equivTo[that, e] = EquivTo.apply(self, that, e)
    private type _equivTo[self <: List, that <: List, e <: Equiv]                             = EquivTo.apply[self, that, e]
    final override type equivTo[that <: List, e <: Equiv] = _equivTo[self, that, e]

    final override  def reverse: reverse = ReverseAppend.apply(self, Nil)
    private type _reverse[self <: List]  = ReverseAppend.apply[self, Nil]
    final override type reverse = _reverse[self]

    final override  def zip[that <: List](that: that): zip[that] = Zip.apply(self, that)
    private type _zip[self <: List,  that <: List]               = Zip.apply[self, that]
    final override type zip[that <: List] = _zip[self, that]

    final override  def unzip: unzip  = Unzip.apply(self)
    private type _unzip[self <: List] = Unzip.apply[self]
    final override type unzip = _unzip[self]

    final override  def force: force  = Force.apply(self)
    private type _force[self <: List] = Force.apply[self]
    final override type force = _force[self]

    final override  def step[n <: Nat](n: n): step[n] = Step.apply(self, n)
    private type _step[self <: List, n <: Nat]        = Step.apply[self, n]
    final override type step[n <: Nat] = _step[self, n]

    final override  def times[n <: Nat](n: n): times[n] = Times.apply(self, n)
    private type _times[self <: List, n <: Nat]         = Times.apply[self, n]
    final override type times[n <: Nat] = _times[self, n]
}
