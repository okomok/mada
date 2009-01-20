

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A): A = {
        v.reduceLeft(op)
    }
}

object ReduceLeft {
    def apply[A, B >: A](v: Vector[A], op: (B, A) => B): B = {
        v.tail.foldLeft[B](v.head)(op)
    }
}

object ReduceRight {
    def apply[A, B >: A](v: Vector[A], op: (A, B) => B): B = {
        v.reverse.reduceLeft(stl.Flip(op))
    }
}
