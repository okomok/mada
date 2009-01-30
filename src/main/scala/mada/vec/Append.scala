

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Append {
    def apply[A](v: Vector[A], w: Vector[A]): Vector[A] = new AppendVector(v, w)
}

private[mada] class AppendVector[A](v: Vector[A], w: Vector[A]) extends Vector[A] {
    private val vn = v.nth
    private val wn = w.nth

    override def start = 0
    override def end = vn.size + wn.size

    override def apply(i: Int) = {
        if (i < vn.size) {
            vn(i)
        } else {
            wn(i - vn.size)
        }
    }
    override def update(i: Int, e: A) = {
        if (i < vn.size) {
            vn(i) = e
        } else {
            wn(i - vn.size) = e
        }
    }
    override def isDefinedAt(i: Int) = {
        if (i < vn.size) {
            vn.isDefinedAt(i)
        } else {
            wn.isDefinedAt(i - vn.size)
        }
    }
}
