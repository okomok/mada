

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Any
 */
trait Any extends scala.Equals {
     def self: self
    type self <: Any

     def asInstanceOfBoolean: asInstanceOfBoolean = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfBoolean"))
    type asInstanceOfBoolean <: Boolean

     def asInstanceOfBox: asInstanceOfBox = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfBox"))
    type asInstanceOfBox <: Box[_]

     def asInstanceOfFunction0: asInstanceOfFunction0 = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfFunction0"))
    type asInstanceOfFunction0 <: Function0

     def asInstanceOfFunction1: asInstanceOfFunction1 = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfFunction1"))
    type asInstanceOfFunction1 <: Function1

     def asInstanceOfFunction2: asInstanceOfFunction2 = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfFunction2"))
    type asInstanceOfFunction2 <: Function2

     def asInstanceOfList: asInstanceOfList = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfList"))
    type asInstanceOfList <: List

     def asInstanceOfNat: asInstanceOfNat = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfNat"))
    type asInstanceOfNat <: Nat

     def asInstanceOfOption: asInstanceOfOption = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfOption"))
    type asInstanceOfOption <: Option

     def asInstanceOfProduct: asInstanceOfProduct = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfProduct"))
    type asInstanceOfProduct <: Product

     def asInstanceOfProduct1: asInstanceOfProduct1 = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfProduct1"))
    type asInstanceOfProduct1 <: Product1

     def asInstanceOfProduct2: asInstanceOfProduct2 = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfProduct2"))
    type asInstanceOfProduct2 <: Product2

     def asInstanceOfUnit: asInstanceOfUnit = `throw`(new scala.UnsupportedOperationException("Any.asInstanceOfUnit"))
    type asInstanceOfUnit <: Unit

     def undual: undual = `throw`(new scala.UnsupportedOperationException("Any.undual"))
    type undual

    override def equals(that: scala.Any) = that match {
        case that: Any => (that canEqual this) && (undual == that.undual)
        case _ => false
    }
    override def hashCode = undual.hashCode
    override def toString = undual.toString
}


object Any {
    // implicit def _boxing[A](from: A): Box[A] = Box(from)
}
