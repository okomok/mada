

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Append {
    def apply[A](v: Vector[A], w: Vector[A]): Vector[A] = new AppendVector(v, w)
}

private[mada] class AppendVector[A](v: Vector[A], w: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = v.nth.size + w.nth.size

    override def apply(i: Int) = {
        if (i < v.nth.size) {
            v.nth(i)
        } else {
            w.nth(i - v.nth.size)
        }
    }
    override def update(i: Int, e: A) = {
        if (i < v.nth.size) {
            v.nth(i) = e
        } else {
            w.nth(i - v.nth.size) = e
        }
    }
    override def isDefinedAt(i: Int) = {
        if (i < v.nth.size) {
            v.nth.isDefinedAt(i)
        } else {
            w.nth.isDefinedAt(i - v.nth.size)
        }
    }
}
