

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Clone {
    def apply[A](v: Vector[A], grainSize: Long): Vector[A] = {
        val out = Vector.arrayVector(new Array[A](v.size.toInt))
        v.parallel(grainSize).copyTo(out)
        out
    }
}
