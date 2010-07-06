

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


trait Product extends Any {
    type self <: Product

    final override  def asInstanceOfProduct = self
    final override type asInstanceOfProduct = self

     def arity: arity
    type arity <: Nat

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product]
}


trait Product1 extends Product {
    type self <: Product1

    final override  def asInstanceOfProduct1 = self
    final override type asInstanceOfProduct1 = self

    final override  def arity = _1N
    final override type arity = _1N

     def _1: _1
    type _1 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product1]
}

trait Product2 extends Product {
    type self <: Product2

    final override  def asInstanceOfProduct2 = self
    final override type asInstanceOfProduct2 = self

    final override  def arity = _2N
    final override type arity = _2N

     def _1: _1
    type _1 <: Any

     def _2: _2
    type _2 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product2]
}
