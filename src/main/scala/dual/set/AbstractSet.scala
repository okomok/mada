

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


trait AbstractSet extends Set {
    final override  def addSeq[xs <: Seq](xs: xs): addSeq[xs] = new AddSeq().apply(self, xs)
    final override type addSeq[xs <: Seq] = AddSeq#apply[self, xs]

    final override  def removeSeq[xs <: Seq](xs: xs): removeSeq[xs] = new RemoveSeq().apply(self, xs)
    final override type removeSeq[xs <: Seq] = RemoveSeq#apply[self, xs]

    final override  def equivTo[that <: Set](that: that): equivTo[that] = new EquivTo().apply(self, that)
    final override type equivTo[that <: Set] = EquivTo#apply[self, that]

    final override  def intersect[that <: Set](that: that): intersect[that] = new Intersect().apply(self, that)
    final override type intersect[that <: Set] = Intersect#apply[self, that]

    final override  def union[that <: Set](that: that): union[that] = self.addSeq(that.toSeq)
    private type _union[self <: Set, that <: Set]                   = self#addSeq[that#toSeq]
    final override type union[that <: Set] = _union[self, that]

    final override  def diff[that <: Set](that: that): diff[that] = self.removeSeq(that.toSeq)
    private type _diff[self <: Set, that <: Set]                  = self#removeSeq[that#toSeq]
    final override type diff[that <: Set] = _diff[self, that]

    final override  def subsetOf[that <: Set](that: that): subsetOf[that] = new SubsetOf().apply(self, that)
    final override type subsetOf[that <: Set] = SubsetOf#apply[self, that]

    override type undual = scala.collection.Set[scala.Any]
    override def canEqual(that: scala.Any) = that.isInstanceOf[Set]
}
