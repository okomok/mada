

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Lifts `scala.Function1` to dual one.
 */
final case class Lift1[T1, R](override val undual: T1 => R) extends Function1 with ValueEquality {
    type self = Lift1[T1, R]
    override type undual = T1 => R

    override  def apply[v1 <: Any](v1: v1): apply[v1] = Box(undual(v1.undual.asInstanceOf[T1]))
    override type apply[v1 <: Any]                    = Box[R]

    override  def canEqual(that: scala.Any) = that.isInstanceOf[Lift1[_, _]]
}


/**
 * Lifts `scala.Function2` to dual one.
 */
final case class Lift2[T1, T2, R](override val undual: (T1, T2) => R) extends Function2 with ValueEquality {
    type self = Lift2[T1, T2, R]
    override type undual = (T1, T2) => R

    override  def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2] = Box(undual(v1.undual.asInstanceOf[T1], v2.undual.asInstanceOf[T2]))
    override type apply[v1 <: Any, v2 <: Any]                                = Box[R]

    override  def canEqual(that: scala.Any) = that.isInstanceOf[Lift2[_, _, _]]
}


/**
 * Lifts `scala.Function3` to dual one.
 */
final case class Lift3[T1, T2, T3, R](override val undual: (T1, T2, T3) => R) extends Function3 with ValueEquality {
    type self = Lift3[T1, T2, T3, R]
    override type undual = (T1, T2, T3) => R

    override  def apply[v1 <: Any, v2 <: Any, v3 <: Any](v1: v1, v2: v2, v3: v3): apply[v1, v2, v3] = Box(undual(v1.undual.asInstanceOf[T1], v2.undual.asInstanceOf[T2], v3.asInstanceOf[T3]))
    override type apply[v1 <: Any, v2 <: Any, v3 <: Any]                                            = Box[R]

    override  def canEqual(that: scala.Any) = that.isInstanceOf[Lift3[_, _, _, _]]
}
