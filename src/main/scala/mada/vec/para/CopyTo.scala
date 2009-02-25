

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B], grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        ThrowIf.differentSize(v, w, "parallel.copyTo")

        (v.divide(grainSize) zip w.divide(grainSize)).
            parallel(1).each{ case (v1, w1) => v1.copyTo(w1) }
        v.parallel(grainSize)
    }
}
