

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Nth {
    def apply[A](v: Vector[A], n: Int): A = {
        ThrowIf.outOfSize(v, n)
        v(v.start + n)
    }

    def apply[A](v: Vector[A], n: Int, e: A): Unit = {
        ThrowIf.outOfSize(v, n)
        v(v.start + n) = e
    }
}
