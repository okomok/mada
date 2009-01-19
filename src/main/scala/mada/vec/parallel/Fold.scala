

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object FoldLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B, grainSize: Long): B = {
        val (v1, v2) = v.splitAt(grainSize)
        if (v2.isEmpty) {
            v1.foldLeft(z)(op)
        } else {
            val u = scala.actors.Futures.future {
                apply(v2, z, op, grainSize)
            }
            v1.foldLeft(z)(op)
            u()
        }
    }
}

object FoldRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B, grainSize: Long): B = {
        v.reverse.parallel(grainSize).foldLeft(z)(stl.Flip(op))
    }
}
