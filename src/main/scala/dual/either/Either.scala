

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package either


object Either


/**
 * The dual Either
 */
sealed abstract class Either extends Any {
    type self <: Either
    type asInstanceOfEither = self

     def get: get
    type get <: Any

     def fold[f <: Function1, g <: Function1](f: f, g: g): fold[f, g]
    type fold[f <: Function1, g <: Function1] <: Any

     def swap: swap
    type swap <: Either

     def joinLeft: joinLeft
    type joinLeft <: Either

     def joinRight: joinRight
    type joinRight <: Either

     def isLeft: isLeft
    type isLeft <: Boolean

     def isRight: isRight
    type isRight <: Boolean

    override type undual <: scala.Either[_, _]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Either]
}


/**
 * The dual Left
 */
final case class Left[e <: Any](override val get: e) extends Either {
    type self = Left[e]

    override type get = e

    override  def fold[f <: Function1, g <: Function1](f: f, g: g): fold[f, g] = f.apply(get)
    override type fold[f <: Function1, g <: Function1] = f#apply[get]

    override  def swap: swap = Right(get)
    override type swap = Right[get]

    override  def joinLeft: joinLeft = get.asInstanceOfEither
    override type joinLeft = get#asInstanceOfEither

    override  def joinRight: joinRight = self
    override type joinRight = self

    override  def isLeft: isLeft = `true`
    override type isLeft = `true`

    override  def isRight: isRight = `false`
    override type isRight = `false`

    override  def undual: undual = scala.Left(get.undual)
    override type undual = scala.Left[get#undual, _]
}


/**
 * The dual Right
 */
final case class Right[e <: Any](override val get: e) extends Either {
    type self = Right[e]

    override type get = e

    override  def fold[f <: Function1, g <: Function1](f: f, g: g): fold[f, g] = g.apply(get)
    override type fold[f <: Function1, g <: Function1] = g#apply[get]

    override  def swap: swap = Left(get)
    override type swap = Left[get]

    override  def joinLeft: joinLeft = self
    override type joinLeft = self

    override  def joinRight: joinRight = get.asInstanceOfEither
    override type joinRight = get#asInstanceOfEither

    override  def isLeft: isLeft = `false`
    override type isLeft = `false`

    override  def isRight: isRight = `true`
    override type isRight = `true`

    override  def undual: undual = scala.Right(get.undual)
    override type undual = scala.Right[_, get#undual]
}
