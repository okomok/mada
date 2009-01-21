

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A): A = {
        v.foldLeft(z)(op)
    }
}

object FoldLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): B = {
        val (x, i, j) = v.triple
        stl.Accumulate(x, i, j, z, op)
    }
}

object FoldRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): B = {
        v.reverse.foldLeft(z)(stl.Flip(op))
    }
}
