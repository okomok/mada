

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


sealed abstract class Result extends Any with ReferenceEquality {
    type self <: Result
    type asInstanceOfPegResult = self

     def get: get
    type get <: Any

     def next: next
    type next <: List

     def successful: successful
    type successful <: Boolean

     def map[f <: Function1](f: f): map[f]
    type map[f <: Function1] <: Result

//     def `match`[s <: Function1, f <: Function1](s: s, f: f): `match`[s, f]
//    type `match`[s <: Function1, f <: Function1] <: Result
}


private[dual]
sealed abstract class AbstractResult extends Result {
//    final override  def `match`[s <: Function1, f <: Function1](s: s, f: f): `match`[s, f] =
//        `if`(successful, s, f).apply(self).asInstanceOfPegResult
//    final override type `match`[s <: Function1, f <: Function1] =
//    `if`[successful, s, f]#apply[self]#asInstanceOfPegResult
}


final case class Success[x <: Any, ys <: List](override val get: x, override val next: ys) extends AbstractResult {
    type self = Success[x, ys]

    override type get = x
    override type next = ys

    override  def successful: successful = `true`
    override type successful             = `true`

    override  def map[f <: Function1](f: f): map[f] = Success(f.apply(get), next)
    override type map[f <: Function1]               = Success[f#apply[get], next]
}


final case class Failure[ys <: List](override val next: ys) extends AbstractResult {
    type self = Failure[ys]

    override  def get: get = noSuchElement("peg.Failure.get")
    override type get      = noSuchElement[_]
    override type next = ys

    override  def successful: successful = `false`
    override type successful             = `false`

    override  def map[f <: Function1](f: f): map[f] = self
    override type map[f <: Function1]               = self
}
