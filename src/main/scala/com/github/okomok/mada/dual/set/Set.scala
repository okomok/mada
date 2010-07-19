

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

    final override  def asInstanceOfSet = self
    final override type asInstanceOfSet = self

     def size: size
    type size <: nat.Peano

     def isEmpty: isEmpty
    type isEmpty <: Boolean

     def add[k <: Any](k: k): add[k]
    type add[k <: Any] <: Set

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Set

     def contains[k <: Any](k: k): contains[k]
    type contains[k <: Any] <: Boolean

     def toList: toList
    type toList <: List

    override type undual = scala.collection.immutable.Set[_]
    override def canEqual(that: scala.Any) = that.isInstanceOf[Set]
}
