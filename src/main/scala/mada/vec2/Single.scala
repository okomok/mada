

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


case class Single[A](var elem: A)


class SingleVector[A](val single: Single[A]) extends Vector[A] {
    override def size = 1
    override def apply(i: Long) = single.elem
    override def update(i: Long, e: A) = single.elem = e

    override def toSingle = single
}


object ToSingle {
    def apply[A](v: Vector[A]): Single[A] = Single(v.first)
}
