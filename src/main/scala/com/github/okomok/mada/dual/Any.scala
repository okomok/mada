

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Any
 */
trait Any {
     def self: self
    type self <: Any

     def asInstanceOfBoolean: asInstanceOfBoolean = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfBoolean <: Boolean

     def asInstanceOfBox: asInstanceOfBox = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfBox <: Box[_]

     def asInstanceOfFunction0: asInstanceOfFunction0 = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfFunction0 <: Function0

     def asInstanceOfFunction1: asInstanceOfFunction1 = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfFunction1 <: Function1

     def asInstanceOfFunction2: asInstanceOfFunction2 = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfFunction2 <: Function2

     def asInstanceOfList: asInstanceOfList = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfList <: List

     def asInstanceOfNat: asInstanceOfNat = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfNat <: Nat

     def asInstanceOfOption: asInstanceOfOption = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfOption <: Option

     def asInstanceOfProduct: asInstanceOfProduct = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfProduct <: Product

     def asInstanceOfProduct1: asInstanceOfProduct1 = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfProduct1 <: Product1

     def asInstanceOfProduct2: asInstanceOfProduct2 = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfProduct2 <: Product2

     def asInstanceOfUnit: asInstanceOfUnit = `throw`(new scala.UnsupportedOperationException)
    type asInstanceOfUnit <: Unit

     def undual: undual = `throw`(new scala.UnsupportedOperationException)
    type undual

    override def hashCode = undual.hashCode
    override def toString = undual.toString
}


object Any {
    // implicit def _boxing[A](from: A): Box[A] = Box(from)
}
