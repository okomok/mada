

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

     def putSeq[xs <: Seq](xs: xs): putSeq[xs]
    type putSeq[xs <: Seq] <: Map

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Map

     def contains[k <: Any](k: k): contains[k]
    type contains[k <: Any] <: Boolean

     def keySet: keySet
    type keySet <: Set

     def toSeq: toSeq
    type toSeq <: Seq

     def keySeq: keySeq
    type keySeq <: Seq

     def valueSeq: valueSeq
    type valueSeq <: Seq

     def equivTo[that <: Map, ve <: Equiv](that: that, ve: ve): equivTo[that, ve]
    type equivTo[that <: Map, ve <: Equiv] <: Boolean

    // left-biased
     def union[that <: Map](that: that): union[that]
    type union[that <: Map] <: Map

    final override type undual = scala.collection.Map[scala.Any, scala.Any]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Map]
}
