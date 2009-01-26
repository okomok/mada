

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        ThrowIf.empty(v, "paralell.reducer")

        val rights = v.divide(grainSize).parallel(1).map({ w => w.reducer(op) }).unparallel
        val lefts = rights.map({ w => w.last }).init.reducer(op)

        rights.head ++
            Vector.undivide(
                lefts.zipWith(rights.tail)({ (l, rs) => rs.map({ r => op(l, r) }) })
            )
    }
}
