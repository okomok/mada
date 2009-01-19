

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Copy {
    def apply[A](v: Vector[A], w: Vector[A], grainSize: Long): Unit = {
        Assert(v.size == w.size)
        val (v1, v2) = v.splitAt(grainSize)
        val (w1, w2) = w.splitAt(grainSize)
        if (v2.isEmpty) {
            Assert(w2.isEmpty)
            stl.Copy(v, 0, v.size, w, 0)
        } else {
            val u2 = scala.actors.Futures.future {
                apply(v2, w2, grainSize)
            }
            stl.Copy(v1, 0, v1.size, w1, 0); u2()
        }
    }
/*
    def apply[A](v : Vector[A], ^ : Vector[A], grainSize: Long): Unit = {
        Assert(v.size == .size)
        val (first, last) = v.pair
        val (result, _) = ^.pair
        impl(v, first, last, ^, result, grainSize)
    }

    def impl[A](v : Vector[A], first: Long, last: Long, ^ : Vector[A], result: Long, grainSize: Long): Unit = {
        val cur = first + grainSize
        if (cur > last) {
            stl.Copy(v, first, last, ^, result)
        } else {
            val u2 = scala.actors.Futures.future {
                impl(v, cur, last, ^, result + grainSize, grainSize)
            }
            stl.Copy(v, first, cur, ^, result); u2()
        }
    }
*/
}
