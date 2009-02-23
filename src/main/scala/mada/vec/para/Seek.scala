

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


import java.util.concurrent.atomic.AtomicReference


private[mada] object Seek {
    def apply[A, B](v: Vector[A], p: A => Boolean, grainSize: Int): Option[A] = {
        Assert(!v.isParallel)

        if (v.isEmpty) {
            None
        } else {
            val ar = new AtomicReference[A]
            val bp = new Breakable1(p, true)
            v.divide(grainSize).
                parallel(1).each({ w => breakingSeek(w, bp, ar) })
            deref(ar)
        }
    }

    private def breakingSeek[A](v: Vector[A], p: Breakable1[A], ar: AtomicReference[A]): Unit = {
        val x = v.seek(p)
        if (!x.isEmpty) {
            ar.compareAndSet(null.asInstanceOf[A], x.get)
            p.break
        }
    }

    private def deref[A](ar: AtomicReference[A]): Option[A] = {
        val a = ar.get
        if (a == null) {
            None
        } else {
            Some(a.asInstanceOf[A])
        }
    }
}
