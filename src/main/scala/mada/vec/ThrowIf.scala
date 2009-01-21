

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ThrowIf {
    def outOfBounds[A](v: Vector[A], i: Int): Unit = {
        if (i < 0 || i >= v.size) {
            throw new IndexOutOfBoundsException(i.toString)
        }
    }

    def empty[A](v: Vector[A], method: String): Unit = {
        if (v.isEmpty) {
            throw new UnsupportedOperationException("empty Vector." + method)
        }
    }

    def differentSize[A, B](v: Vector[A], w: Vector[B]): Unit = {
        if (v.size != w.size) {
            throw new UnsupportedOperationException("size is different: " + v.size + " and " + w.size)
        }
    }

    def invalidGrainSize(grainSize: Int): Unit = {
        if (grainSize <= 0) {
            throw new java.lang.IllegalArgumentException("grain size must be positive")
        }
    }
}
