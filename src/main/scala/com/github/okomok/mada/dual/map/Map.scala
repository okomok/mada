

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

    final override  def asInstanceOfMap = self
    final override type asInstanceOfMap = self

     def size: size
    type size <: Nat

     def isEmpty: isEmpty
    type isEmpty <: Boolean

     def get[k <: Any](k: k): get[k]
    type get[k <: Any] <: Option

     def put[k <: Any, v <: Any](k: k, v: v): put[k, v]
    type put[k <: Any, v <: Any] <: Map

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Map

    final  def contains[k <: Any](k: k): contains[k] = get(k).isEmpty.not
    final type contains[k <: Any] = get[k]#isEmpty#not

     def keySet: keySet
    type keySet <: Set

     def toList: toList
    type toList <: List

     def keyList: keyList
    type keyList <: List

     def valueList: valueList
    type valueList <: List

    final override type undual = scala.collection.immutable.Map[_, _]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Map]
}
