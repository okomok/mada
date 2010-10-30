

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


final case class Tuple3[v1 <: Any, v2 <: Any, v3 <: Any](override val _1: v1, override val _2: v2, override val _3: v3) extends AbstractProduct3 {
    type self = Tuple3[v1, v2, v3]

    override type _1 = v1
    override type _2 = v2
    override type _3 = v3

    override  def undual: undual = scala.Tuple3(_1.undual, _2.undual, _3.undual)
    override type undual         = scala.Tuple3[_1#undual, _2#undual, _3#undual]
}

object Tuple3 {
    def box[T1, T2, T3](t: scala.Tuple3[T1, T2, T3]): Tuple3[Box[T1], Box[T2], Box[T3]] = Tuple3(Box(t._1), Box(t._2), Box(t._3))
    def box[T1, T2, T3](v1: T1, v2: T2, v3: T3): Tuple3[Box[T1], Box[T2], Box[T3]] = Tuple3(Box(v1), Box(v2), Box(v3))
}
