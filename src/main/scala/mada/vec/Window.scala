

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// Note: fromRange(0, 100).window(20, 100).window(-20, 100) is ok.

object Window {
    def apply[A](v: Vector[A], n: Long, m: Long): Vector[A] = new WindowVector(v, n, m)
}

class WindowVector[A](override val * : Vector[A], n: Long, m: Long) extends Adapter[A, A] {
    Assert(n <= m)

    override def size = m - n
    override def mapIndex(i: Long) = n + i

    override def window(_n: Long, _m: Long) = *.window(n + _n, n + _m)
}
