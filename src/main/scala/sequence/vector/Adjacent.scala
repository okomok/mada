

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Adjacent[A](val _1: Vector[A], _2: Int) extends Vector[Vector[A]] {
    Precondition.positive(_2, "adjacent count")

    override def start = 0
    override def end = java.lang.Math.max(0, _1.size - _2 + 1)
    override def apply(i: Int) = new Region(_1.nth, i, i + _2)
}
