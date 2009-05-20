

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.para


private[mada] object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A, grainSize: Int): A = {
        util.assert(!IsParallel(v))
        (vector.single(z) ++ v).parallel(grainSize).reduce(op)
    }
}

private[mada] object Folder {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A, grainSize: Int): Vector[A] = {
        util.assert(!IsParallel(v))
        (vector.single(z) ++ v).parallel(grainSize).reducer(op)
    }
}


private[mada] object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): A = {
        util.assert(!IsParallel(v))
        ThrowIf.empty(v, "paralell.reduce")

        v.parallelRegions(grainSize).map{ w => w.reduce(op) }.
            reduce(op)
    }
}

private[mada] object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): Vector[A] = {
        util.assert(!IsParallel(v))
        ThrowIf.empty(v, "paralell.reducer")

        val rss = v.parallelRegions(grainSize).map{ w => w.reducer(op) }
        if (rss.size == 1) {
            return rss.head
        }

        val ls = rss.init.map{ w => w.last }.reducer(op)
        rss.head ++
            vector.undivide(
                (ls zip rss.tail).parallel(1).map{ case (l, rs) => rs.map{ r => op(l, r) } } )
    }
}
