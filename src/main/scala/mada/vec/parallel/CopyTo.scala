

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B], grainSize: Long): Vector[A] = {
        if (v.size != w.size) {
            throw new java.lang.IllegalArgumentException("size is different: " + v.size + " and " + w.size)
        }
        impl(v, w, grainSize)
        v
    }

    def impl[A, B >: A](v: Vector[A], w: Vector[B], grainSize: Long): Unit = {
        val (v1, v2) = v.splitAt(grainSize)
        val (w1, w2) = w.splitAt(grainSize)
        if (v2.isEmpty) {
            Assert(w2.isEmpty)
            v.copyTo(w)
        } else {
            val u2 = scala.actors.Futures.future {
                impl(v2, w2, grainSize)
            }
            v1.copyTo(w1); u2()
        }
    }
}
