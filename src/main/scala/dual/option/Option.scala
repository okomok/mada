

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
    type asInstanceOfOption = self

     def isEmpty: isEmpty
    type isEmpty <: Boolean

    final  def nonEmpty: nonEmpty = isEmpty.not
    final type nonEmpty = isEmpty#not

     def get: get
    type get <: Any

     def getOrElse[f <: Function0](f: f): getOrElse[f]
    type getOrElse[f <: Function0] <: Any

    final  def isDefined: isDefined = isEmpty.not
    final type isDefined = isEmpty#not

     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: Option

     def flatMap[f <: Function1](f: f): flatMap[f]
    type flatMap[f <: Function1] <: Option

     def filter[f <: Function1](f: f): filter[f]
    type filter[f <: Function1] <: Option

     def exists[f <: Function1](f: f): exists[f]
    type exists[f <: Function1] <: Boolean

     def foreach[f <: Function1](f: f): foreach[f]
    final type foreach[f <: Function1] = Unit

    final  def orElse[f <: Function0](f: f): orElse[f] = `if`(isEmpty, f, const0(self)).apply.asInstanceOfOption
    final type orElse[f <: Function0] = `if`[isEmpty, f, const0[self]]#apply#asInstanceOfOption

    override type undual <: scala.Option[_]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Option]
}


/**
 * The dual None
 */
sealed abstract class None extends Option {
    type self = None

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

    override  def exists[f <: Function1](f: f): exists[f] = `false`
    override type exists[f <: Function1] = `false`

    override  def foreach[f <: Function1](f: f): foreach[f] = Unit

    override  def undual: undual = scala.None
    override type undual = scala.None.type
}


/**
 * The dual Some
 */
final case class Some[e <: Any](override val get: e) extends Option {
    type self = Some[e]

    override  def isEmpty: isEmpty = `false`
    override type isEmpty = `false`

    override type get = e

    override  def getOrElse[f <: Function0](f: f): getOrElse[f] = get
    override type getOrElse[f <: Function0] = get

    override  def map[f <: Function1](f: f): map[f] = Some(f.apply(get))
    override type map[f <: Function1] = Some[f#apply[get]]

    override  def flatMap[f <: Function1](f: f): flatMap[f] = f.apply(get).asInstanceOfOption
    override type flatMap[f <: Function1] = f#apply[get]#asInstanceOfOption

    override  def filter[f <: Function1](f: f): filter[f] = `if`(f.apply(get).asInstanceOfBoolean, const0(self), const0(None)).apply.asInstanceOfOption
    override type filter[f <: Function1] = `if`[f#apply[get]#asInstanceOfBoolean, const0[self], const0[None]]#apply#asInstanceOfOption

    override  def exists[f <: Function1](f: f): exists[f] = f.apply(get).asInstanceOfBoolean
    override type exists[f <: Function1] = f#apply[get]#asInstanceOfBoolean

    override  def foreach[f <: Function1](f: f): foreach[f] = { f.apply(get); Unit }

    override  def undual: undual = scala.Some(get.undual)
    override type undual = scala.Some[get#undual]
}


private[mada] object _Option {
    val None = new None{}
}
