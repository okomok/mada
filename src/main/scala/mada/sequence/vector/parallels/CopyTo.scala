

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector.parallels


private[mada] object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B], grainSize: Int): Vector[A] = {
        util.assert(!IsParallel(v))
        precondition.sameSize(v, w, "copyTo")

        (v.divide(grainSize) zip w.divide(grainSize)).
            parallel(1).each{ case (v1, w1) => v1.copyTo(w1) }
        v
    }
}