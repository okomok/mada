

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package vector


/**
 * The dual random-access list
 */
trait Vector extends List {
    type self <: List
    type asInstanceOfList = self

    type tail <: Vector
    type ::[e <: Any] <: Vector
    type clear <: Vector
    type append[that <: List] <: Vector
    type map[f <: Function1] <: Vector
    type flatMap[f <: Function1] <: Vector
    type flatten <: Vector
    type filter[f <: Function1] <: Vector
     def sort: sort
    type sort <: Vector
    type sortWith[o <: Ordering] <: Vector
    type scanLeft[z <: Any, f <: Function2] <: Vector
    type scanRight[z <: Any, f <: Function2] <: Vector
    type init <: Vector
    type take[n <: Nat] <: Vector
    type drop[n <: Nat] <: Vector
    type slice[n <: Nat, m <: Nat]  <: Vector
    type takeWhile[f <: Function1] <: Vector
    type dropWhile[f <: Function1] <: Vector
    type reverse <: Vector
    type zip[that <: Vector] <: Vector
    type force <: Vector
    type step[n <: Nat] <: Vector
    type times[n <: Nat] <: Vector

    override  def canEqual(that: scala.Any) = that.isInstanceOf[Vector]
}
