

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import java.util.concurrent.atomic.AtomicReference


object Find {
    def apply[A, B](v: Vector[A], p: A => Boolean, grainSize: Long): Option[A] = {
        if (v.isEmpty) {
            None
        } else {
            val ar = new AtomicReference[A]
            impl(v, new Breakable1(p, true), grainSize, ar)
            val a = ar.get
            if (a == null) {
                None
            } else {
                Some(a.asInstanceOf[A])
            }
        }
    }

    def impl[A](v: Vector[A], p: Breakable1[A], grainSize: Long, ar: AtomicReference[A]): Unit = {
        val (v1, v2) = v.splitAt(grainSize)
        if (v2.isEmpty) {
            breakingFind(v, p, ar)
        } else {
            val u2 = scala.actors.Futures.future {
                impl(v2, p, grainSize, ar)
            }
            breakingFind(v1, p, ar); u2()
        }
    }

    private def breakingFind[A](v: Vector[A], p: Breakable1[A], ar: AtomicReference[A]): Unit = {
        val x = v.find(p)
        if (!x.isEmpty) {
            p.break
            ar.set(x.get)
        }
    }
}
