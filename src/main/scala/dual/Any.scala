

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


object Any


/**
 * The dual Any
 */
trait Any extends scala.Equals {
    @returnThis
     def self: self
    type self <: Any

     def asInstanceOfBoolean: asInstanceOfBoolean = castError("Boolean")
    type asInstanceOfBoolean <: Boolean

//    final  def isInstanceOfBoolean: isInstanceOfBoolean = checkInstance(boolean._Boolean.typeid)
//    final type isInstanceOfBoolean = checkInstance[boolean._Boolean.typeid]

     def asInstanceOfBox: asInstanceOfBox = castError("Box")
    type asInstanceOfBox <: Box[_]

     def asInstanceOfEither: asInstanceOfEither = castError("Either")
    type asInstanceOfEither <: Either

     def asInstanceOfFunction0: asInstanceOfFunction0 = castError("Function0")
    type asInstanceOfFunction0 <: Function0

     def asInstanceOfFunction1: asInstanceOfFunction1 = castError("Function1")
    type asInstanceOfFunction1 <: Function1

     def asInstanceOfFunction2: asInstanceOfFunction2 = castError("Function2")
    type asInstanceOfFunction2 <: Function2

     def asInstanceOfList: asInstanceOfList = castError("List")
    type asInstanceOfList <: List

     def asInstanceOfNat: asInstanceOfNat = castError("Nat")
    type asInstanceOfNat <: Nat

     def asInstanceOfNatDense: asInstanceOfNatDense = castError("NatDense")
    type asInstanceOfNatDense <: nat.Dense

     def asInstanceOfNatPeano: asInstanceOfNatPeano = castError("NatPeano")
    type asInstanceOfNatPeano <: nat.Peano

     def asInstanceOfMap: asInstanceOfMap = castError("Map")
    type asInstanceOfMap <: Map

     def asInstanceOfMapBSTree: asInstanceOfMapBSTree = castError("MapBSTree")
    type asInstanceOfMapBSTree <: map.bstree.BSTree

     def asInstanceOfSet: asInstanceOfSet = castError("Set")
    type asInstanceOfSet <: Set

     def asInstanceOfOption: asInstanceOfOption = castError("Option")
    type asInstanceOfOption <: Option

     def asInstanceOfEquiv: asInstanceOfEquiv = castError("Equiv")
    type asInstanceOfEquiv <: Equiv

     def asInstanceOfOrdering: asInstanceOfOrdering = castError("Ordering")
    type asInstanceOfOrdering <: Ordering

     def asInstanceOfOrderingResult: asInstanceOfOrderingResult = castError("OrderingResult")
    type asInstanceOfOrderingResult <: ordering.Result

     def asInstanceOfProduct: asInstanceOfProduct = castError("Product")
    type asInstanceOfProduct <: Product

     def asInstanceOfProduct1: asInstanceOfProduct1 = castError("Product1")
    type asInstanceOfProduct1 <: Product1

     def asInstanceOfProduct2: asInstanceOfProduct2 = castError("Product2")
    type asInstanceOfProduct2 <: Product2

     def asInstanceOfUnit: asInstanceOfUnit = castError("Unit")
    type asInstanceOfUnit <: Unit

    /**
     * Escapes from the dual world.
     */
     def undual: undual = unsupported("undual")
    type undual

    override def equals(that: scala.Any) = that match {
        case that: Any => (this eq that) || (that canEqual this) && (undual == that.undual)
        case _ => false
    }
    override def hashCode = undual.hashCode
    override def toString = "dual." + undual.toString

    /**
     * The type id as bit flags.
     */
//    protected  def typeid: typeid = throw new Error
//    protected type typeid <: nat.Dense

    /**
     * Trivial helper to throw UnsupportedOperationException
     */
    protected  def unsupported(name: Predef.String): unsupported[_] = `throw`(new java.lang.UnsupportedOperationException("dual." + name))
    protected type unsupported[_] = `throw`[scala.UnsupportedOperationException]

    private def castError(name: Predef.String) = throw new java.lang.ClassCastException(toString + " is not instance of " + name)

//    private  def checkInstance[id <: nat.Dense](id: id): checkInstance[id] = (typeid & id).isZero.not
//    private type checkInstance[id <: nat.Dense] = typeid# &[id]#isZero#not
}


/**
 * Mixin for a metatype whose `equals` is reference-equality.
 */
trait ReferenceEquality extends Any {
    final override lazy val undual: undual = new scala.AnyRef{}
    final override type undual = scala.AnyRef
    final override  def canEqual(that: scala.Any) = true
}
