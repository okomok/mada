

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


object Seq extends Common


trait Seq extends Any {
    type self <: Seq
    type asInstanceOfSeq = self

    @constantTime
     def head: head
    type head <: Any

    @constantTime
     def tail: tail
    type tail <: Seq

     def addFirst[e <: Any](e: e): addFirst[e] = unsupported("Seq.addFirst")
    type addFirst[e <: Any] <: Seq

     def addLast[e <: Any](e: e): addLast[e] = unsupported("Seq.addLast")
    type addLast[e <: Any] <: Seq

    @constantTime
     def clear: clear
    type clear <: Seq

    final  def foreach[f <: Function1](f: f): foreach[f] = new Foreach().apply(self, f)
    final type foreach[f <: Function1] = Foreach#apply[self, f]

    @constantTime
     def isEmpty: isEmpty
    type isEmpty <: Boolean

    @equivalentTo("isEmpty.not")
    final  def nonEmpty: nonEmpty = isEmpty.not
    final type nonEmpty           = isEmpty#not

    @constantTime
     def size: size
    type size <: Nat

    @aliasOf("size")
    final  def length: length = size
    final type length = size

     def append[that <: Seq](that: that): append[that]
    type append[that <: Seq] <: Seq

     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: Seq

    final  def flatMap[f <: Function1](f: f): flatMap[f] = map(f).flatten
    final type flatMap[f <: Function1]                   = map[f]#flatten

     def flatten: flatten
    type flatten <: Seq

     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: Seq

     def partition[f <: Function1](f: f): partition[f]
    type partition[f <: Function1] <: Product2

     def sort[o <: Ordering](o: o): sort[o]
    type sort[o <: Ordering] <: Seq

    final  def forall[f <: Function1](f: f): forall[f] = exists(f.not).not.asInstanceOf[forall[f]]
    final type forall[f <: Function1]                  = exists[f#not]#not

    final  def exists[f <: Function1](f: f): exists[f] = find(f).nonEmpty
    final type exists[f <: Function1]                  = find[f]#nonEmpty

    final  def count[f <: Function1](f: f): count[f] = new Count().apply(self, f)
    final type count[f <: Function1] = Count#apply[self, f]

    final  def find[f <: Function1](f: f): find[f] = new Find().apply(self, f)
    final type find[f <: Function1] = Find#apply[self, f]

     def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f]
    type foldLeft[z <: Any, f <: Function2] <: Any

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

    final  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = new ReduceLeft().apply(self, f)
    final type reduceLeft[f <: Function2] = ReduceLeft#apply[self, f]

    final  def reduceRight[f <: Function2](f: f): reduceRight[f] = new ReduceRight().apply(self, f)
    final type reduceRight[f <: Function2] = ReduceRight#apply[self, f]

     def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f]
    type scanLeft[z <: Any, f <: Function2] <: Seq

     def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f]
    type scanRight[z <: Any, f <: Function2] <: Seq

     def nth[n <: Nat](n: n): nth[n]
    type nth[n <: Nat] <: Any

     def last: last
    type last <: Any

     def init: init
    type init <: Seq

     def take[n <: Nat](n: n): take[n]
    type take[n <: Nat] <: Seq

     def drop[n <: Nat](n: n): drop[n]
    type drop[n <: Nat] <: Seq

    final  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = take(m).drop(n)
    final type slice[n <: Nat, m <: Nat]                          = take[m]#drop[n]

     def takeWhile[f <: Function1](f: f): takeWhile[f]
    type takeWhile[f <: Function1] <: Seq

     def dropWhile[f <: Function1](f: f): dropWhile[f]
    type dropWhile[f <: Function1] <: Seq

     def span[f <: Function1](f: f): span[f]
    type span[f <: Function1] <: Product2

     def splitAt[n <: Nat](n: n): splitAt[n]
    type splitAt[n <: Nat] <: Product2

    final  def equivTo[that <: Seq, e <: Equiv](that: that, e: e): equivTo[that, e] = new EquivTo().apply(self, that, e)
    final type equivTo[that <: Seq, e <: Equiv] = EquivTo#apply[self, that, e]

     def reverse: reverse
    type reverse <: Seq

     def zip[that <: Seq](that: that): zip[that]
    type zip[that <: Seq] <: Seq

     def unzip: unzip
    type unzip <: Product2

     def toList: toList
    type toList <: List
}
