

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


object Map extends Common


/**
 * The dual Map
 */
trait Map extends Any {
    type self <: Map
    type asInstanceOfMap = self

     def size: size
    type size <: Nat

     def isEmpty: isEmpty
    type isEmpty <: Boolean

     def clear: clear
    type clear <: Map

     def get[k <: Any](k: k): get[k]
    type get[k <: Any] <: Option

     def put[k <: Any, v <: Any](k: k, v: v): put[k, v]
    type put[k <: Any, v <: Any] <: Map

    final  def putSeq[xs <: Seq](xs: xs): putSeq[xs] = new PutSeq().apply(self, xs)
    final type putSeq[xs <: Seq] = PutSeq#apply[self, xs]

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Map

    final  def contains[k <: Any](k: k): contains[k] = get(k).isEmpty.not
    final type contains[k <: Any]                    = get[k]#isEmpty#not

     def keySet: keySet
    type keySet <: Set

     def toList: toList
    type toList <: List

     def keyList: keyList
    type keyList <: List

     def valueList: valueList
    type valueList <: List

    final  def equivTo[that <: Map, ve <: Equiv](that: that, ve: ve): equivTo[that, ve] = new EquivTo().apply(self, that, ve)
    final type equivTo[that <: Map, ve <: Equiv] = EquivTo#apply[self, that, ve]

    // left-biased
    final  def union[that <: Map](that: that): union[that] = that.putSeq(self.toList)
    private type _union[self <: Map, that <: Map]          = that#putSeq[self#toList]
    final type union[that <: Map] = _union[self, that]

    final override type undual = scala.collection.Map[scala.Any, scala.Any]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Map]
}
