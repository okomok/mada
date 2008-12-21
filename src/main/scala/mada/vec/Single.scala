

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


class SingleVector[A](var single: A) extends Vector[A] {
    override def size = 1
    override def apply(i: Long) = single
    override def update(i: Long, e: A) = single = e
}
