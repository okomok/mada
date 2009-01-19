

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Long): A = {
        val (v1, v2) = v.splitAt(grainSize)
        if (v2.isEmpty) {
            v1.reduceLeft(op)
        } else {
            val u = scala.actors.Futures.future {
                apply(v2, op, grainSize)
            }
            op(v1.reduceLeft(op), u())
        }
    }
}
