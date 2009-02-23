

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A, grainSize: Int): A = {
        Assert(!v.isParallel)
        (Vector.single(z) ++ v).parallel(grainSize).reduce(op)
    }
}

private[mada] object Folder {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        (Vector.single(z) ++ v).parallel(grainSize).reducer(op)
    }
}


private[mada] object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): A = {
        Assert(!v.isParallel)
        ThrowIf.empty(v, "paralell.reduce")

        v.divide(grainSize).
            parallel(1).map({ w => w.reduce(op) }).
                unparallel.reduce(op)
    }
}

private[mada] object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        ThrowIf.empty(v, "paralell.reducer")

        val rss = v.divide(grainSize).parallel(1).map({ w => w.reducer(op) }).unparallel
        if (rss.size == 1) {
            return rss.head.
                parallel(grainSize)
        }

        val ls = rss.init.map({ w => w.last }).reducer(op)
        (rss.head ++
            Vector.undivide(
                (ls zip rss.tail).parallel(1).map({ case (l, rs) => rs.map({ r => op(l, r) }) })
            )).
                parallel(grainSize)
    }
}
