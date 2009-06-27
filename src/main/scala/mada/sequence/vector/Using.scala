

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Using[A, E](_1: Vector[A], _2: Auto[E]) extends Auto[Vector[A]] {
    override def autoRef = _1
    override def autoBegin = _2.autoBegin
    override def autoEnd = _2.autoEnd
}
