

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Copy {
    def apply[A](v: Vector[A], w: Vector[A], grainSize: Long): Unit = {
        Assert(v.size == w.size)
        impl(v, w, grainSize)
    }

    def impl[A](v: Vector[A], w: Vector[A], grainSize: Long): Unit = {
        Assert(v.size == w.size)
        val (v1, v2) = v.splitAt(grainSize)
        val (w1, w2) = w.splitAt(grainSize)
        if (v2.isEmpty) {
            stl.Copy(v, 0, v.size, w, 0)
        } else {
            val u = scala.actors.Futures.future {
                apply(v2, w2, grainSize)
            }
            stl.Copy(v, 0, v.size, w, 0)
            u()
        }
    }
}
