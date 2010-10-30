

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


final case class Tuple1[v1 <: Any](override val _1: v1) extends AbstractProduct1 {
    type self = Tuple1[v1]

    override type _1 = v1

    override  def undual: undual = scala.Tuple1(_1.undual)
    override type undual         = scala.Tuple1[_1#undual]
}

object Tuple1 {
    def box[T1](t: scala.Tuple1[T1]): Tuple1[Box[T1]] = Tuple1(Box(t._1))
    def box[T1](v1: T1): Tuple1[Box[T1]] = Tuple1(Box(v1))
}
