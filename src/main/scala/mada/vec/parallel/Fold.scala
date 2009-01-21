

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A, grainSize: Int): A = {
        if (v.isEmpty) {
            z
        } else {
            op(z, v.parallel(grainSize).reduce(op))
        }
    }
}
