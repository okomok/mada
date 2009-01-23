

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): A = {
        ThrowIf.empty(v, "paralell.reduce")
        v.divide(grainSize).
            parallel(1).map({ w => w.reduceLeft(op) }).
                unparallel.reduceLeft(op)
    }
}
