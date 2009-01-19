

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Equals {
    def apply[A](v: Vector[A], w: Any, grainSize: Long): Boolean = w match {
        case w: Vector[_] => v.parallel(grainSize).equalsTo(w)
        case _ => false
    }
}

object EqualsTo {
    def apply[A, B](v: Vector[A], w: Vector[B], grainSize: Long): Boolean = {
        v.parallel(grainSize).equalsWith(w)(stl.EqualTo)
    }
}

object EqualsWith {
    def apply[A, B](v: Vector[A], w: Vector[B], p: (A, B) => Boolean, grainSize: Long): Boolean = {
        if (v.size != w.size) {
            false
        } else {
            impl(v, w, new Breakable2(p, false), grainSize)
        }
    }

    def impl[A, B](v: Vector[A], w: Vector[B], p: Breakable2[A, B], grainSize: Long): Boolean = {
        Assert(v.size == w.size)
        val (v1, v2) = v.splitAt(grainSize)
        val (w1, w2) = w.splitAt(grainSize)
        if (v2.isEmpty) {
            Assert(w2.isEmpty)
            breakingEquals(v, w, p)
        } else {
            val u2 = scala.actors.Futures.future {
                impl(v2, w2, p, grainSize)
            }
            breakingEquals(v1, w1, p) && u2()
        }
    }

    private def breakingEquals[A, B](v: Vector[A], w: Vector[B], p: Breakable2[A, B]): Boolean = {
        val x = v.equalsWith(w)(p)
        if (!x) {
            p.break
        }
        x
    }
}
