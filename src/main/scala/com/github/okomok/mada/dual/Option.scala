

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import option._


object Option


/**
 * The dual Option
 */
sealed abstract class Option extends Any {
    type self <: Option

    final override  def asInstanceOfOption = self
    final override type asInstanceOfOption = self

    /**
     * Get a value of this option.
     */
     def get: get
    type get <: Any // Not metageneric

    /**
     * If the option is nonempty return its value,
     * otherwise return the result of evaluating a default expression.
     */
    final  def getOrElse[f <: Function0](f: f): getOrElse[f] = GetOrElse(self, f).apply
    final type getOrElse[f <: Function0] = GetOrElse[self, f]#apply

    /**
     * Returns true iif the option is a <code>Some</code>(...).
     */
     def isEmpty: isEmpty
    type isEmpty <: Boolean

    @equivalentTo("isEmpty.not")
    final  def isDefined: isDefined = isEmpty.not
    final type isDefined = isEmpty#not

    final  def map[f <: Function1](f: f): map[f] = isEmpty.`if`(always0(None), byLazy(Some(f.apply(get)))).apply.asInstanceOf[map[f]]
    final type map[f <: Function1] = isEmpty#`if`[always0[None], byLazy[Some[f#apply[get]]]]#apply

    override type undual <: scala.Option[_]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Option]
}


/**
 * The dual None
 */
sealed abstract class None extends Option {
    override  def self = this
    override type self = None

    override  def get: get = `throw`(new scala.NoSuchElementException("dual.None.get"))
    override type get = `throw`[scala.NoSuchElementException]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def undual: undual = scala.None
    override type undual = scala.None.type
}


/**
 * The dual Some
 */
final case class Some[e <: Any](e: e) extends Option {
    override  def self = this
    override type self = Some[e]

    override  val get: get = e
    override type get = e

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  def undual: undual = scala.Some(e.undual)
    override type undual = scala.Some[e#undual]
}


private[mada] object _Option {
    val None = new None{}
}
