

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


// AtomicReference isn't enough because null is a valid element of vector.
import java.util.concurrent.atomic.AtomicMarkableReference


private object ParallelSeek {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Option[A] = {
        util.assert(!isParallel(_1))

        if (_1.isEmpty) {
            None
        } else {
            val ar = new AtomicMarkableReference[A](util.nullInstance, false) // mark means "found".
            val bp = new Breakable1(_2, true)
            _1.parallelRegions(_3).each{ w => breakingSeek(w, bp, ar) }
            if (ar.isMarked) Some(ar.getReference) else None
        }
    }

    private def breakingSeek[A](v: Vector[A], p: Breakable1[A], ar: AtomicMarkableReference[A]): Unit = {
        val x = v.seek(p) // can tell a lie after someone wins.
        if (!x.isEmpty) {
            ar.compareAndSet(util.nullInstance, x.get, false, true) // try to win the race.
            p.break
        }
    }
}
