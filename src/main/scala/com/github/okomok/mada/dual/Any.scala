

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


object Any {
    // useless
    // implicit def _boxing[A](from: A): Box[A] = Box(from)
}


/**
 * The dual Any
 */
trait Any extends scala.Equals {
     def self: self
    type self <: Any

//     def typeid: typeid
//    type typeid <: nat.Dense

     def asInstanceOfBoolean: asInstanceOfBoolean = unsupported("dual.Any.asInstanceOfBoolean")
    type asInstanceOfBoolean <: Boolean

     def asInstanceOfBox: asInstanceOfBox = unsupported("dual.Any.asInstanceOfBox")
    type asInstanceOfBox <: Box[_]

     def asInstanceOfEither: asInstanceOfEither = unsupported("dual.Any.asInstanceOfEither")
    type asInstanceOfEither <: Either

     def asInstanceOfFunction0: asInstanceOfFunction0 = unsupported("dual.Any.asInstanceOfFunction0")
    type asInstanceOfFunction0 <: Function0

     def asInstanceOfFunction1: asInstanceOfFunction1 = unsupported("dual.Any.asInstanceOfFunction1")
    type asInstanceOfFunction1 <: Function1

     def asInstanceOfFunction2: asInstanceOfFunction2 = unsupported("dual.Any.asInstanceOfFunction2")
    type asInstanceOfFunction2 <: Function2

     def asInstanceOfList: asInstanceOfList = unsupported("dual.Any.asInstanceOfList")
    type asInstanceOfList <: List

     def asInstanceOfNatDense: asInstanceOfNatDense = unsupported("dual.Any.asInstanceOfNatDense")
    type asInstanceOfNatDense <: nat.Dense

     def asInstanceOfMap: asInstanceOfMap = unsupported("dual.Any.asInstanceOfMap")
    type asInstanceOfMap <: Map

     def asInstanceOfNatPeano: asInstanceOfNatPeano = unsupported("dual.Any.asInstanceOfNatPeano")
    type asInstanceOfNatPeano <: nat.Peano

     def asInstanceOfOption: asInstanceOfOption = unsupported("dual.Any.asInstanceOfOption")
    type asInstanceOfOption <: Option

     def asInstanceOfOrdering: asInstanceOfOrdering = unsupported("dual.Any.asInstanceOfOrdering")
    type asInstanceOfOrdering <: Ordering

     def asInstanceOfOrderingResult: asInstanceOfOrderingResult = unsupported("dual.Any.asInstanceOfOrderingResult")
    type asInstanceOfOrderingResult <: ordering.Result

     def asInstanceOfProduct: asInstanceOfProduct = unsupported("dual.Any.asInstanceOfProduct")
    type asInstanceOfProduct <: Product

     def asInstanceOfProduct1: asInstanceOfProduct1 = unsupported("dual.Any.asInstanceOfProduct1")
    type asInstanceOfProduct1 <: Product1

     def asInstanceOfProduct2: asInstanceOfProduct2 = unsupported("dual.Any.asInstanceOfProduct2")
    type asInstanceOfProduct2 <: Product2

     def asInstanceOfUnit: asInstanceOfUnit = unsupported("dual.Any.asInstanceOfUnit")
    type asInstanceOfUnit <: Unit

     def undual: undual = unsupported("dual.Any.undual")
    type undual

    override def equals(that: scala.Any) = that match {
        case that: Any => (that canEqual this) && (undual == that.undual)
        case _ => false
    }
    override def hashCode = undual.hashCode
    override def toString = undual.toString

    protected  def unsupported(msg: Predef.String) = `throw`(new scala.UnsupportedOperationException(msg))
    protected type unsupported[_] = `throw`[scala.UnsupportedOperationException]
}
