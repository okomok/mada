

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


// AtomicReference isn't enough because null is a valid element of vector.
import java.util.concurrent.atomic.AtomicMarkableReference


private[mada] object Seek {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Option[A] = {
        Assert(!IsParallel(v))

        if (v.isEmpty) {
            None
        } else {
            val ar = new AtomicMarkableReference[A](Java.`null`, false) // mark means "found".
            val bp = new Breakable1(p, true)
            v.parallelRegions(grainSize).each{ w => breakingSeek(w, bp, ar) }
            if (ar.isMarked) Some(ar.getReference) else None
        }
    }

    private def breakingSeek[A](v: Vector[A], p: Breakable1[A], ar: AtomicMarkableReference[A]): Unit = {
        val x = v.seek(p) // can tell a lie after someone wins.
        if (!x.isEmpty) {
            ar.compareAndSet(Java.`null`, x.get, false, true) // try to win the race.
            p.break
        }
    }
}
