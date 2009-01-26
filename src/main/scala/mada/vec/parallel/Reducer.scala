

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        ThrowIf.empty(v, "paralell.reducer")

        val rss = v.divide(grainSize).parallel(1).map({ w => w.reducer(op) }).unparallel
        if (rss.size == 1) {
            return rss.head
        }

        val ls = rss.init.map({ w => w.last }).reducer(op)
        rss.head ++
            Vector.undivide(
                (ls zip rss.tail).parallel(1).map({ case (l, rs) => rs.map({ r => op(l, r) }) })
            )
    }
}
