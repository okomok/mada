

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


class Into[A](val vector: Vector[A], val start: Long) extends (A => Any) {
    private var i = start
    override def apply(e: A) = { vector(i) = e; i += 1 }
    final def index = i
}
