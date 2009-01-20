

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ThrowsIf {
    def differentSize[A, B](v: Vector[A], w: Vector[B]): Unit = {
        if (v.size != w.size) {
            throw new UnsupportedOperationException("size is different: " + v.size + " and " + w.size)
        }
    }

    def invalidGrainSize(grainSize: Long): Unit = {
        if (grainSize <= 0) {
            throw new java.lang.IllegalArgumentException("grain size must be positive")
        }
    }
}
