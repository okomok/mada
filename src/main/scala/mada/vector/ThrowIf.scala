

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object ThrowIf {
    def empty[A](v: Vector[A], method: String): Unit = {
        if (v.isEmpty) {
            throw new UnsupportedOperationException("empty vector." + method)
        }
    }

    def differentSize[A, B](v: Vector[A], w: Vector[B], method: String): Unit = {
        if (v.size != w.size) {
            throw new UnsupportedOperationException("vector." + method + " of different size Vectors: " + v.size + " and " + w.size)
        }
    }

    def nonpositive(i: Int, what: String): Unit = {
        if (i <= 0) {
            throw new IllegalArgumentException("nonpositive " + what + ": " + i)
        }
    }
}
