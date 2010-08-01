

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

final case class Tuple2[v1 <: Any, v2 <: Any](override val _1: v1, override val _2: v2) extends AbstractProduct2 {
    type self = Tuple2[v1, v2]

    override type _1 = v1
    override type _2 = v2

    override  def undual: undual = scala.Tuple2(_1.undual, _2.undual)
    override type undual         = scala.Tuple2[_1#undual, _2#undual]
}
