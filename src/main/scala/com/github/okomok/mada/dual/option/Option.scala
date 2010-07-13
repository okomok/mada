

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package option


object Option


/**
 * The dual Option
 */
sealed abstract class Option extends Any {
    type self <: Option

    final override  def asInstanceOfOption = self
    final override type asInstanceOfOption = self

    /**
     * Returns true iif the option is a <code>Some</code>(...).
     */
     def isEmpty: isEmpty
    type isEmpty <: Boolean

    /**
     * Get a value of this option.
     */
     def get: get
    type get <: Any // Not metageneric

    /**
     * If the option is nonempty return its value,
     * otherwise return the result of evaluating a default expression.
     */
     def getOrElse[f <: Function0](f: f): getOrElse[f]
    type getOrElse[f <: Function0] <: Any

    @equivalentTo("isEmpty.not")
    final  def isDefined: isDefined = isEmpty.not
    final type isDefined = isEmpty#not

    @equivalentTo("if (isEmpty) None else Some(f(get)))")
     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: Option

    @equivalentTo("if (isEmpty) None else f(get))")
     def flatMap[f <: Function1](f: f): flatMap[f]
    type flatMap[f <: Function1] <: Option

    @equivalentTo("if (isEmpty || f(get)) this else None")
     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: Option

    @equivalentTo("!isEmpty && f(get)")
    final  def exists[f <: Function1](f: f): exists[f] = isEmpty.not && f.apply(get).asInstanceOfBoolean
    final type exists[f <: Function1] = isEmpty#not# &&[f#apply[get]#asInstanceOfBoolean]

    @equivalentTo("if (!isEmpty) f(get)")
    def foreach[f <: Function1](f: f): foreach[f]
    final type foreach[f <: Function1] = Unit

    @equivalentTo("if (isEmpty) f() else this")
    final  def orElse[f <: Function0](f: f): orElse[f] = `if`(isEmpty, f, always0(self)).apply.asInstanceOfOption
    final type orElse[f <: Function0] = `if`[isEmpty, f, always0[self]]#apply#asInstanceOfOption

    override type undual <: scala.Option[_]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Option]
}


/**
 * The dual None
 */
sealed abstract class None extends Option {
    override  val self = this
    override type self = None

    override  def isEmpty: isEmpty = `true`
    override type isEmpty = `true`

    override  def get: get = `throw`(new scala.NoSuchElementException("dual.None.get"))
    override type get = `throw`[scala.NoSuchElementException]

    override  def getOrElse[f <: Function0](f: f): getOrElse[f] = f.apply
    override type getOrElse[f <: Function0] = f#apply

    override  def map[f <: Function1](f: f): map[f] = None
    override type map[f <: Function1] = None

    override  def flatMap[f <: Function1](f: f): flatMap[f] = None
    override type flatMap[f <: Function1] = None

    override  def filter[f <: Function1](f: f): filter[f] = None
    override type filter[f <: Function1] = None

    override  def foreach[f <: Function1](f: f): foreach[f] = Unit

    override  def undual: undual = scala.None
    override type undual = scala.None.type
}


/**
 * The dual Some
 */
final case class Some[e <: Any](e: e) extends Option {
    override  val self = this
    override type self = Some[e]

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override  val get: get = e
    override type get = e

    override  def getOrElse[f <: Function0](f: f): getOrElse[f] = get
    override type getOrElse[f <: Function0] = get

    override  def map[f <: Function1](f: f): map[f] = Some(f.apply(get))
    override type map[f <: Function1] = Some[f#apply[get]]

    override  def flatMap[f <: Function1](f: f): flatMap[f] = f.apply(get).asInstanceOfOption
    override type flatMap[f <: Function1] = f#apply[get]#asInstanceOfOption

    override  def filter[f <: Function1](f: f): filter[f] = `if`(f.apply(get).asInstanceOfBoolean, always0(self), always0(None)).apply.asInstanceOfOption
    override type filter[f <: Function1] = `if`[f#apply[get]#asInstanceOfBoolean, always0[self], always0[None]]#apply#asInstanceOfOption

    override  def foreach[f <: Function1](f: f): foreach[f] = { f.apply(get); Unit }

    override  def undual: undual = scala.Some(e.undual)
    override type undual = scala.Some[e#undual]
}


private[mada] object _Option {
    val None = new None{}
}
