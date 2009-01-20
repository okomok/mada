

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B], grainSize: Long): Vector[A] = {
        ThrowIf.differentSize(v, w)
        v.divide(grainSize).zip(w.divide(grainSize)).
            parallel(1).foreach({ case (v1, w1) => v1.copyTo(w1) })
        v
    }
}
