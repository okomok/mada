

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * The dual Any
 */
trait Any extends scala.Equals {
     def self: self
    type self <: Any

     def asInstanceOfBoolean: asInstanceOfBoolean = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfBoolean"))
    type asInstanceOfBoolean <: Boolean

     def asInstanceOfBox: asInstanceOfBox = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfBox"))
    type asInstanceOfBox <: Box[_]

     def asInstanceOfFunction0: asInstanceOfFunction0 = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfFunction0"))
    type asInstanceOfFunction0 <: Function0

     def asInstanceOfFunction1: asInstanceOfFunction1 = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfFunction1"))
    type asInstanceOfFunction1 <: Function1

     def asInstanceOfFunction2: asInstanceOfFunction2 = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfFunction2"))
    type asInstanceOfFunction2 <: Function2

     def asInstanceOfList: asInstanceOfList = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfList"))
    type asInstanceOfList <: List

     def asInstanceOfNatPeano: asInstanceOfNatPeano = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfNatPeano"))
    type asInstanceOfNatPeano <: nat.Peano

     def asInstanceOfNatDense: asInstanceOfNatDense = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfNatDense"))
    type asInstanceOfNatDense <: nat.Dense

     def asInstanceOfOption: asInstanceOfOption = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfOption"))
    type asInstanceOfOption <: Option

     def asInstanceOfProduct: asInstanceOfProduct = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfProduct"))
    type asInstanceOfProduct <: Product

     def asInstanceOfProduct1: asInstanceOfProduct1 = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfProduct1"))
    type asInstanceOfProduct1 <: Product1

     def asInstanceOfProduct2: asInstanceOfProduct2 = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfProduct2"))
    type asInstanceOfProduct2 <: Product2

     def asInstanceOfUnit: asInstanceOfUnit = `throw`(new scala.UnsupportedOperationException("dual.Any.asInstanceOfUnit"))
    type asInstanceOfUnit <: Unit

     def undual: undual = `throw`(new scala.UnsupportedOperationException("dual.Any.undual"))
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
