

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait AbstractSet extends Set {
    final override  def addList[xs <: List](xs: xs): addList[xs] = AddList.apply(self, xs)
    final override type addList[xs <: List]                      = AddList.apply[self, xs]

    final override  def removeList[xs <: List](xs: xs): removeList[xs] = RemoveList.apply(self, xs)
    final override type removeList[xs <: List]                         = RemoveList.apply[self, xs]

    final override  def equal[that <: Set](that: that): equal[that] = Equal.apply(self, that)
    final override type equal[that <: Set]                          = Equal.apply[self, that]

    final override  def intersect[that <: Set](that: that): intersect[that] = Intersect.apply(self, that)
    final override type intersect[that <: Set]                              = Intersect.apply[self, that]

    final override  def union[that <: Set](that: that): union[that] = addList(that.toList)
    final override type union[that <: Set]                          = addList[that#toList]

    final override  def diff[that <: Set](that: that): diff[that] = removeList(that.toList)
    final override type diff[that <: Set]                         = removeList[that#toList]

    final override  def subsetOf[that <: Set](that: that): subsetOf[that] = SubsetOf.apply(self, that)
    final override type subsetOf[that <: Set]                             = SubsetOf.apply[self, that]

    override type undual = scala.collection.Set[scala.Any]
    override  def canEqual(that: scala.Any) = that.isInstanceOf[Set]
}
