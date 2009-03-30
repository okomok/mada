

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


import java.util.concurrent.atomic.AtomicReference


private[mada] object Seek {
    def apply[A, B](v: Vector[A], p: A => Boolean, grainSize: Int): Option[A] = {
        Assert(!IsParallel(v))

        if (v.isEmpty) {
            None
        } else {
            val ar = new AtomicReference[A]
            val bp = new Breakable1(p, true)
            v.parallelRegions(grainSize).each{ w => breakingSeek(w, bp, ar) }
            Java.toOption(ar.get)
        }
    }

    private def breakingSeek[A](v: Vector[A], p: Breakable1[A], ar: AtomicReference[A]): Unit = {
        val x = v.seek(p)
        if (!x.isEmpty) {
            ar.compareAndSet(Java.fromNone, x.get)
            p.break
        }
    }
}
