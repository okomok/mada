

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait AbstractSet extends Set {
    final override  def addList[xs <: List](xs: xs): addList[xs] = AddList.apply(self, xs)
    private type _addList[self <: Set, xs <: List]               = AddList.apply[self, xs]
    final override type addList[xs <: List] = _addList[self, xs]

    final override  def removeList[xs <: List](xs: xs): removeList[xs] = RemoveList.apply(self, xs)
    private type _removeList[self <: Set, xs <: List]                  = RemoveList.apply[self, xs]
    final override type removeList[xs <: List] = _removeList[self, xs]

    final override  def equivTo[that <: Set](that: that): equivTo[that] = EquivTo.apply(self, that)
    private type _equivTo[self <: Set, that <: Set]                     = EquivTo.apply[self, that]
    final override type equivTo[that <: Set] = _equivTo[self, that]

    final override  def intersect[that <: Set](that: that): intersect[that] = Intersect.apply(self, that)
    private type _intersect[self <: Set, that <: Set]                       = Intersect.apply[self, that]
    final override type intersect[that <: Set] = _intersect[self, that]

    final override  def union[that <: Set](that: that): union[that] = self.addList(that.toList)
    private type _union[self <: Set, that <: Set]                   = self.addList[that#toList]
    final override type union[that <: Set] = _union[self, that]

    final override  def diff[that <: Set](that: that): diff[that] = self.removeList(that.toList)
    private type _diff[self <: Set, that <: Set]                  = self.removeList[that#toList]
    final override type diff[that <: Set] = _diff[self, that]

    final override  def subsetOf[that <: Set](that: that): subsetOf[that] = SubsetOf.apply(self, that)
    private type _subsetOf[self <: Set, that <: Set]                      = SubsetOf.apply[self, that]
    final override type subsetOf[that <: Set] = _subsetOf[self, that]

    override type undual = scala.collection.Set[scala.Any]
    override  def canEqual(that: scala.Any) = that.isInstanceOf[Set]
}
