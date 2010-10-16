

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Lifts `scala.Function1` to boxed one.
 */
final case class Lift1[T1, R](override val undual: T1 => R) extends Function1 {
    type self = Lift1[T1, R]
    override type undual = T1 => R

    override  def apply[v1 <: Any](v1: v1): apply[v1] = Box(undual.apply(v1.undual.asInstanceOf[T1]))
    override type apply[v1 <: Any]                    = Box[R]

    override def equals(that: scala.Any) = if (that == null) false else that equals undual
    override def hashCode = undual.hashCode
    override def toString = undual.toString
}
