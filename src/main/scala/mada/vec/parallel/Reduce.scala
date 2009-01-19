

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object ReduceLeft {
    def apply[A, B >: A](v: Vector[A], op: (B, A) => B, grainSize: Long): B = {
        v.tail.parallel(grainSize).foldLeft[B](v.head)(op)
    }
}

object ReduceRight {
    def apply[A, B >: A](v: Vector[A], op: (A, B) => B, grainSize: Long): B = {
        v.reverse.parallel(grainSize).reduceLeft(stl.Flip(op))
    }
}
