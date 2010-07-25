

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
    final val self: self = this.asInstanceOf[self]
    type self <: Any

    final def asInstanceOfBoolean: asInstanceOfBoolean = this.asInstanceOf[asInstanceOfBoolean]
    type asInstanceOfBoolean <: Boolean

//    final  def isInstanceOfBoolean: isInstanceOfBoolean = checkInstance(boolean._Boolean.typeid)
//    final type isInstanceOfBoolean = checkInstance[boolean._Boolean.typeid]

    final def asInstanceOfBox: asInstanceOfBox = this.asInstanceOf[asInstanceOfBox]
    type asInstanceOfBox <: Box[_]

    final def asInstanceOfEither: asInstanceOfEither = this.asInstanceOf[asInstanceOfEither]
    type asInstanceOfEither <: Either

    final def asInstanceOfFunction0: asInstanceOfFunction0 = this.asInstanceOf[asInstanceOfFunction0]
    type asInstanceOfFunction0 <: Function0

    final def asInstanceOfFunction1: asInstanceOfFunction1 = this.asInstanceOf[asInstanceOfFunction1]
    type asInstanceOfFunction1 <: Function1

    final def asInstanceOfFunction2: asInstanceOfFunction2 = this.asInstanceOf[asInstanceOfFunction2]
    type asInstanceOfFunction2 <: Function2

    final def asInstanceOfList: asInstanceOfList = this.asInstanceOf[asInstanceOfList]
    type asInstanceOfList <: List

    final def asInstanceOfNat: asInstanceOfNat = this.asInstanceOf[asInstanceOfNat]
    type asInstanceOfNat <: Nat

    final def asInstanceOfNatDense: asInstanceOfNatDense = this.asInstanceOf[asInstanceOfNatDense]
    type asInstanceOfNatDense <: nat.Dense

    final def asInstanceOfNatPeano: asInstanceOfNatPeano = this.asInstanceOf[asInstanceOfNatPeano]
    type asInstanceOfNatPeano <: nat.Peano

    final def asInstanceOfMap: asInstanceOfMap = this.asInstanceOf[asInstanceOfMap]
    type asInstanceOfMap <: Map

    final def asInstanceOfMapBSTree: asInstanceOfMapBSTree = this.asInstanceOf[asInstanceOfMapBSTree]
    type asInstanceOfMapBSTree <: map.bstree.BSTree

    final def asInstanceOfSet: asInstanceOfSet = this.asInstanceOf[asInstanceOfSet]
    type asInstanceOfSet <: Set

    final def asInstanceOfOption: asInstanceOfOption = this.asInstanceOf[asInstanceOfOption]
    type asInstanceOfOption <: Option

    final def asInstanceOfEquiv: asInstanceOfEquiv = this.asInstanceOf[asInstanceOfEquiv]
    type asInstanceOfEquiv <: Equiv

    final def asInstanceOfOrdering: asInstanceOfOrdering = this.asInstanceOf[asInstanceOfOrdering]
    type asInstanceOfOrdering <: Ordering

    final def asInstanceOfOrderingResult: asInstanceOfOrderingResult = this.asInstanceOf[asInstanceOfOrderingResult]
    type asInstanceOfOrderingResult <: ordering.Result

    final def asInstanceOfProduct: asInstanceOfProduct = this.asInstanceOf[asInstanceOfProduct]
    type asInstanceOfProduct <: Product

    final def asInstanceOfProduct1: asInstanceOfProduct1 = this.asInstanceOf[asInstanceOfProduct1]
    type asInstanceOfProduct1 <: Product1

    final def asInstanceOfProduct2: asInstanceOfProduct2 = this.asInstanceOf[asInstanceOfProduct2]
    type asInstanceOfProduct2 <: Product2

    final def asInstanceOfUnit: asInstanceOfUnit = this.asInstanceOf[asInstanceOfUnit]
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
    protected type unsupported[_] = `throw`[_]

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
