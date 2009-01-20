

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Long): A = {
        if (v.isEmpty) {
            throw new UnsupportedOperationException("empty Vector reduce")
        }
        v.divide(grainSize).parallel.map({ w => w.reduceLeft(op) }).reduceLeft(op)
    }
}
