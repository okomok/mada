

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


object Set extends Common


/**
 * The dual Set
 */
trait Set extends Any {
    type self <: Set
    type asInstanceOfSet = self

     def size: size
    type size <: Nat

     def isEmpty: isEmpty
    type isEmpty <: Boolean

     def add[k <: Any](k: k): add[k]
    type add[k <: Any] <: Set

    final  def addList[xs <: List](xs: xs): addList[xs] = new AddList().apply(self, xs)
    final type addList[xs <: List] = AddList#apply[self, xs]

     def clear: clear
    type clear <: Set

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Set

    final  def removeList[xs <: List](xs: xs): removeList[xs] = new RemoveList().apply(self, xs)
    final type removeList[xs <: List] = RemoveList#apply[self, xs]

     def contains[k <: Any](k: k): contains[k]
    type contains[k <: Any] <: Boolean

     def toList: toList
    type toList <: List

    final  def equivTo[that <: Set](that: that): equivTo[that] = new EquivTo().apply(self, that)
    final type equivTo[that <: Set] = EquivTo#apply[self, that]

    final  def intersect[that <: Set](that: that): intersect[that] = new Intersect().apply(self, that)
    final type intersect[that <: Set] = Intersect#apply[self, that]

    final  def union[that <: Set](that: that): union[that] = new Union().apply(self, that)
    final type union[that <: Set] = Union#apply[self, that]

    final  def diff[that <: Set](that: that): diff[that] = new Diff().apply(self, that)
    final type diff[that <: Set] = Diff#apply[self, that]

    final  def subsetOf[that <: Set](that: that): subsetOf[that] = new SubsetOf().apply(self, that)
    final type subsetOf[that <: Set] = SubsetOf#apply[self, that]

    override type undual = scala.collection.Set[scala.Any]
    override def canEqual(that: scala.Any) = that.isInstanceOf[Set]
}
