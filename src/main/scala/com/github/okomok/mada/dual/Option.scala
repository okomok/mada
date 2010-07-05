

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


import option._


/**
 * The dual Option
 */
sealed abstract class Option extends Any {
    @returnThis
     def self: self
    type self <: Option

    /**
     * Get a value of this option.
     */
     def get: get
    type get <: Any // Not metageneric

    /**
     * If the option is nonempty return its value,
     * otherwise return the result of evaluating a default expression.
     */
    final  def getOrElse[f <: Function0](f: f): getOrElse[f] = new GetOrElse().apply(self, f)
    final type getOrElse[f <: Function0] = GetOrElse#apply[self, f]

    /**
     * Returns true iif the option is a <code>Some</code>(...).
     */
     def isEmpty: isEmpty
    type isEmpty <: Boolean

    @equivalentTo("isEmpty.not")
     def isDefined: isDefined = isEmpty.not
    type isDefined = isEmpty#not

    def undual: undual
    final type undual = scala.Option[scala.Any]
}


/**
 * The dual None
 */
sealed abstract class None extends Option {
    override  val self = this
    override type self = None

    override  def get = throw new NoSuchElementException("dual.None.get")
    override type get = Nothing

    override  def isEmpty = `true`
    override type isEmpty = `true`

    override def undual = scala.None
}


/**
 * The dual Some
 */
final case class Some[e <: Any](e: e) extends Option {
    override  val self = this
    override type self = Some[e]

    override  val get = e
    override type get = e

    override  def isEmpty = `false`
    override type isEmpty = `false`

    override def undual = scala.Some(e.undual)
}


object Option {
    private[mada] val _None = new None{}
}
