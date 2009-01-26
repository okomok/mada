

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A): A = {
        (Vector.single(z) ++ v).reduce(op)
    }
}

object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A): A = {
        ThrowIf.empty(v, "reduce")
        v.reduceLeft(op)
    }
}


object Folder {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A): Vector[A] = {
        (Vector.single(z) ++ v).reducer(op)
    }
}

object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A): Vector[A] = {
        ThrowIf.empty(v, "reducer")
        v.reducerLeft(op)
    }
}
