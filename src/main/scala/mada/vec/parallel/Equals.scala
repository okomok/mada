

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel



object Equals {
    def apply[A](v: Vector[A], w: Any, grainSize: Long): Boolean = w match {
        case w: Vector[_] => v.parallel(grainSize).equalsWith(w)(stl.EqualTo)
        case _ => false
    }
}

object EqualsWith {
    class Breakable[A, B](p: (A, B) => Boolean) extends ((A, B) => Boolean) {
        private val isBreak = new java.util.concurrent.atomic.AtomicBoolean(false)
        override def apply(a: A, b: B) = if (isBreak.get) false else p(a, b)
        final def break = isBreak.set(true)
    }

    def apply[A, B](v: Vector[A], w: Vector[B], p: (A, B) => Boolean, grainSize: Long): Boolean = {
        if (v.size != w.size) {
            false
        } else {
            impl(v, w, new Breakable(p), grainSize)
        }
    }

    def impl[A, B](v: Vector[A], w: Vector[B], p: Breakable[A, B], grainSize: Long): Boolean = {
        Assert(v.size == w.size)
        val (v1, v2) = v.splitAt(grainSize)
        val (w1, w2) = w.splitAt(grainSize)
        if (v2.isEmpty) {
            Assert(w2.isEmpty)
            breakingEqual(v, w, p)
        } else {
            val u2 = scala.actors.Futures.future {
                impl(v2, w2, p, grainSize)
            }
            breakingEqual(v1, w1, p) && u2()
        }
    }

    private def breakingEqual[A, B](v: Vector[A], w: Vector[B], p: Breakable[A, B]) = {
        Assert(v.size == w.size)
        val x = stl.Equal(v, 0, v.size, w, 0, p)
        if (!x) {
            p.break
        }
        x
    }
}
