

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

     def addList[xs <: List](xs: xs): addList[xs]
    type addList[xs <: List] <: Set

     def clear: clear
    type clear <: Set

     def remove[k <: Any](k: k): remove[k]
    type remove[k <: Any] <: Set

     def removeList[xs <: List](xs: xs): removeList[xs]
    type removeList[xs <: List] <: Set

     def contains[k <: Any](k: k): contains[k]
    type contains[k <: Any] <: Boolean

     def toList: toList
    type toList <: List

     def equivTo[that <: Set](that: that): equivTo[that]
    type equivTo[that <: Set] <: Boolean

     def intersect[that <: Set](that: that): intersect[that]
    type intersect[that <: Set] <: Set

     def union[that <: Set](that: that): union[that]
    type union[that <: Set] <: Set

     def diff[that <: Set](that: that): diff[that]
    type diff[that <: Set] <: Set

     def subsetOf[that <: Set](that: that): subsetOf[that]
    type subsetOf[that <: Set] <: Boolean
}
