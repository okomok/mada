

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FoldLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): B = {
        val (first, last) = v.pair
        stl.Accumulate(v, first, last, z, op)
    }
}

object FoldRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): B = {
        v.reverse.foldLeft(z)(stl.Flip(op))
    }
}
