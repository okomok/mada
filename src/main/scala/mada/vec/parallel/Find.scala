

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Find {
    def apply[A, B](v: Vector[A], p: A => Boolean, grainSize: Long): Option[A] = {
        if (v.isEmpty) {
            None
        } else {
            impl(v, new Breakable1(p, true), grainSize)
        }
    }

    def impl[A](v: Vector[A], p: Breakable1[A], grainSize: Long): Option[A] = {
        val (v1, v2) = v.splitAt(grainSize)
        if (v2.isEmpty) {
            breakingFind(v, p)
        } else {
            val u2 = scala.actors.Futures.future {
                apply(v2, p, grainSize)
            }
            val x = breakingFind(v, p)
            if (x.isEmpty) {
                u2()
            } else {
                x
            }
        }
    }

    private def breakingFind[A](v: Vector[A], p: Breakable1[A]): Option[A] = {
        val x = v.find(p)
        if (!x.isEmpty) {
            p.break
        }
        x
    }
}
