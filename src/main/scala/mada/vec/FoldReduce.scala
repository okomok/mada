

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Fold {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A): A = {
        (Vector.single(z) ++ v).reduce(op)
    }
}

private[mada] object Folder {
    def apply[A](v: Vector[A], z: A, op: (A, A) => A): Vector[A] = {
        (Vector.single(z) ++ v).reducer(op)
    }
}


private[mada] object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A): A = {
        ThrowIf.empty(v, "reduce")
        v.reduceLeft(op)
    }
}

private[mada] object Reducer {
    def apply[A](v: Vector[A], op: (A, A) => A): Vector[A] = {
        ThrowIf.empty(v, "reducer")
        v.reducerLeft(op)
    }
}
