

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

     def asBoolean: asBoolean = unsupported("Any.asBoolean")
    type asBoolean <: Boolean

//    final  def isInstanceOfBoolean: isInstanceOfBoolean = checkInstance(boolean._Boolean.typeid)
//    final type isInstanceOfBoolean = checkInstance[boolean._Boolean.typeid]

     def asEither: asEither = unsupported("Any.asBoolean")
    type asEither <: Either

     def asFunction0: asFunction0 = unsupported("Any.asFunction0")
    type asFunction0 <: Function0

     def asFunction1: asFunction1 = unsupported("Any.asFunction1")
    type asFunction1 <: Function1

     def asFunction2: asFunction2 = unsupported("Any.asFunction2")
    type asFunction2 <: Function2

     def asFunction3: asFunction3 = unsupported("Any.asFunction3")
    type asFunction3 <: Function3

     def asList: asList = unsupported("Any.asList")
    type asList <: List

     def asMap: asMap = unsupported("Any.asMap")
    type asMap <: Map

     def asMapBSTree: asMapBSTree = unsupported("Any.asMapBSTree")
    type asMapBSTree <: map.bstree.BSTree

     def asNat: asNat = unsupported("Any.asNat")
    type asNat <: Nat

     def asNatDense: asNatDense = unsupported("Any.asNatDense")
    type asNatDense <: nat.Dense

     def asNatPeano: asNatPeano = unsupported("Any.asNatPeano")
    type asNatPeano <: nat.Peano

     def asSet: asSet = unsupported("Any.asSet")
    type asSet <: Set

     def asOption: asOption = unsupported("Any.asOption")
    type asOption <: Option

     def asEquiv: asEquiv = unsupported("Any.asEquiv")
    type asEquiv <: Equiv

     def asOrdering: asOrdering = unsupported("Any.asOrdering")
    type asOrdering <: Ordering

     def asOrderingResult: asOrderingResult = unsupported("Any.asOrderingResult")
    type asOrderingResult <: ordering.Result

     def asPeg: asPeg = unsupported("Any.asPeg")
    type asPeg <: Peg

     def asPegResult: asPegResult = unsupported("Any.asPegResult")
    type asPegResult <: peg.Result

     def asProduct: asProduct = unsupported("Any.asProduct")
    type asProduct <: Product

     def asProduct1: asProduct1 = unsupported("Any.asProduct1")
    type asProduct1 <: Product1

     def asProduct2: asProduct2 = unsupported("Any.asProduct2")
    type asProduct2 <: Product2

     def asProduct3: asProduct3 = unsupported("Any.asProduct3")
    type asProduct3 <: Product3

     def asUnit: asUnit = unsupported("Any.asUnit")
    type asUnit <: Unit

    /**
     * Returns the natural ordering.
     */
     def naturalOrdering: naturalOrdering = unsupported("Any.naturalOrdering")
    type naturalOrdering <: Ordering

    /**
     * Escapes from the dual world.
     */
     def undual: undual = unsupported("Any.undual")
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
    protected  def unsupported(what: Predef.String): unsupported[_] = throw new UnsupportedOperationException("dual." + what)
    protected type unsupported[_] = Nothing

    /**
     * Trivial helper to throw UnsupportedOperationException
     */
    protected  def noSuchElement(what: Predef.String): noSuchElement[_] = throw new NoSuchElementException("dual." + what)
    protected type noSuchElement[_] = Nothing

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
