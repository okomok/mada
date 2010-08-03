

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


trait AbstractMap extends Map {
    final override  def putSeq[xs <: Seq](xs: xs): putSeq[xs] = new PutSeq().apply(self, xs)
    final override type putSeq[xs <: Seq] = PutSeq#apply[self, xs]

    final override  def contains[k <: Any](k: k): contains[k] = get(k).isEmpty.not
    final override type contains[k <: Any]                    = get[k]#isEmpty#not

    final override  def equivTo[that <: Map, ve <: Equiv](that: that, ve: ve): equivTo[that, ve] = new EquivTo().apply(self, that, ve)
    final override type equivTo[that <: Map, ve <: Equiv] = EquivTo#apply[self, that, ve]

    final override  def union[that <: Map](that: that): union[that] = that.putSeq(self.toSeq)
    private type _union[self <: Map, that <: Map]                   = that#putSeq[self#toSeq]
    final override type union[that <: Map] = _union[self, that]
}
