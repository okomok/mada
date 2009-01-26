

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Append {
    def apply[A](v: Vector[A], w: Vector[A]): Vector[A] = new AppendVector(v, w)
}

private[mada] class AppendVector[A](v: Vector[A], w: Vector[A]) extends Vector[A] {
    override def size = v.size + w.size
    override def apply(i: Int) = {
        if (i < v.size) {
            v(i)
        } else {
            w(i - v.size)
        }
    }
    override def update(i: Int, e: A) = {
        if (i < v.size) {
            v(i) = e
        } else {
            w(i - v.size) = e
        }
    }
}
