

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package views


private[mada] final case class View[it <: Iterator](it: it) extends Seq {
    type self = View[it]

    override  def begin: begin = it
    override type begin        = it

    override  def view: view = self
    override type view       = self

    override  def head: head = it.deref
    override type head       = it#deref

    override  def tail: tail = View(it.next)
    override type tail       = View[it#next]

    override  def addFirst[e <: Any](e: e): addFirst[e] = throw new Error
    override type addFirst[e <: Any] <: Seq

    override  def addLast[e <: Any](e: e): addLast[e] = throw new Error
    override type addLast[e <: Any] <: Seq

    override  def clear: clear = View(iterator.End)
    override type clear        = View[iterator.End]

    override  def foreach[f <: Function1](f: f): foreach[f] = new Foreach().apply(it, f)
    override type foreach[f <: Function1] = Foreach#apply[it, f]

    override  def isEmpty: isEmpty = it.isEnd
    override type isEmpty          = it#isEnd

    override  def nonEmpty: nonEmpty = isEmpty.not
    override type nonEmpty           = isEmpty#not

    override  def size: size = unsupported("View.size")
    override type size       = unsupported[_]

    override  def length: length = new Length().apply(it)
    override type length         = Length#apply[it]

    override  def append[that <: Seq](that: that): append[that] = throw new Error
    override type append[that <: Seq] <: Seq

    override  def map[f <: Function1](f: f): map[f] = new Map().apply(it, f)
    override type map[f <: Function1] = Map#apply[it, f]

    override  def flatMap[f <: Function1](f: f): flatMap[f] = map(f).flatten
    override type flatMap[f <: Function1]                   = map[f]#flatten

    override  def flatten: flatten = throw new Error
    override type flatten <: Seq

    override  def filter[f <: Function1](f: f): filter[f] = throw new Error
    override type filter[f <: Function1] <: Seq

    override  def partition[f <: Function1](f: f): partition[f] = throw new Error
    override type partition[f <: Function1] <: Product2

    override  def sort[o <: Ordering](o: o): sort[o] = throw new Error
    override type sort[o <: Ordering] <: Seq

    override  def forall[f <: Function1](f: f): forall[f] = throw new Error
    override type forall[f <: Function1] <: Boolean

    override  def exists[f <: Function1](f: f): exists[f] = throw new Error
    override type exists[f <: Function1] <: Boolean

    override  def count[f <: Function1](f: f): count[f] = throw new Error
    override type count[f <: Function1] <: Nat

    override  def find[f <: Function1](f: f): find[f] = throw new Error
    override type find[f <: Function1] <: Option

    override  def foldLeft[z <: Any, f <: Function2](z: z, f: f): foldLeft[z, f] = throw new Error
    override type foldLeft[z <: Any, f <: Function2] <: Any

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = throw new Error
    override type foldRight[z <: Any, f <: Function2] <: Any

    override  def reduceLeft[f <: Function2](f: f): reduceLeft[f] = throw new Error
    override type reduceLeft[f <: Function2] <: Any

    override  def reduceRight[f <: Function2](f: f): reduceRight[f] = throw new Error
    override type reduceRight[f <: Function2] <: Any

    override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = throw new Error
    override type scanLeft[z <: Any, f <: Function2] <: Seq

    override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = throw new Error
    override type scanRight[z <: Any, f <: Function2] <: Seq

    override  def nth[n <: Nat](n: n): nth[n] = throw new Error
    override type nth[n <: Nat] <: Any

    override  def last: last = throw new Error
    override type last <: Any

    override  def init: init = throw new Error
    override type init <: Seq

    override  def take[n <: Nat](n: n): take[n] = throw new Error
    override type take[n <: Nat] <: Seq

    override  def drop[n <: Nat](n: n): drop[n] = throw new Error
    override type drop[n <: Nat] <: Seq

    override  def slice[n <: Nat, m <: Nat](n: n, m: m): slice[n, m] = take(n).drop(m)
    override type slice[n <: Nat, m <: Nat]                          = take[n]#drop[m]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = throw new Error
    override type takeWhile[f <: Function1] <: Seq

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = throw new Error
    override type dropWhile[f <: Function1] <: Seq

    override  def span[f <: Function1](f: f): span[f] = throw new Error
    override type span[f <: Function1] <: Product2

    override  def splitAt[n <: Nat](n: n): splitAt[n] = throw new Error
    override type splitAt[n <: Nat] <: Product2

    override  def equivTo[that <: Seq, e <: Equiv](that: that, e: e): equivTo[that, e] = throw new Error
    override type equivTo[that <: Seq, e <: Equiv] <: Boolean

    override  def reverse: reverse = unsupported("View.reverse")
    override type reverse          = unsupported[_]

    override  def zip[that <: Seq](that: that): zip[that] = throw new Error
    override type zip[that <: Seq] <: Seq

    override  def unzip: unzip = throw new Error
    override type unzip <: Product2

    override  def toList: toList = throw new Error
    override type toList <: List

    override  def fromSuper[that <: Seq](that: that): fromSuper[that] = throw new Error
    override type fromSuper[that <: Seq] <: Seq
}
