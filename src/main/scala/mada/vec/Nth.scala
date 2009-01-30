

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Nth {
    def apply[A](v: Vector[A], n: Int): A = {
        v(v.start + n)
    }

    def apply[A](v: Vector[A], n: Int, e: A): Unit = {
        v(v.start + n) = e
    }
}

private[mada] object IsDefinedAtNth {
    def apply[A](v: Vector[A], i: Int): Boolean = {
        v.isDefinedAt(v.start + i)
    }
}
