

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


trait AbstractMap extends Map {
    final override  def putList[xs <: List](xs: xs): putList[xs] = PutList.apply(self, xs)
    final override type putList[xs <: List]                      = PutList.apply[self, xs]

    final override  def contains[k <: Any](k: k): contains[k] = get(k).isEmpty.not
    final override type contains[k <: Any]                    = get[k]#isEmpty#not

    final override  def equivTo[that <: Map, ve <: Equiv](that: that, ve: ve): equivTo[that, ve] = EquivTo.apply(self, that, ve)
    final override type equivTo[that <: Map, ve <: Equiv]                                        = EquivTo.apply[self, that, ve]

    final override  def union[that <: Map](that: that): union[that] = that.putList(self.toList)
    final override type union[that <: Map]                          = that#putList[self#toList]
}
