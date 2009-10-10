

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class Force[A](_1: Vector[A]) extends TransformAdapter[A] with NotWritable[A] {
    override protected val underlying = fromJList(ToJArrayList(_1))

    override def force: Vector[A] = this // force-force fusion
}
