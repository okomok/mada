

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class WindowVector[A](override val * : Vector[A], n: Long, m: Long) extends Adapter[A, A] {
    override def size = m - n
    override def apply(i: Long) = *(n + i)
    override def update(i: Long, e: A) = *(n + i) = e

    override def window(_n: Long, _m: Long) = *.window(n + _n, n + _m)
}
