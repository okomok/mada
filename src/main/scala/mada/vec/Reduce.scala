

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ReduceLeft {
    def apply[A, B >: A](v: Vector[A], op: (B, A) => B): B = {
        ThrowIf.empty(v, "reduceLeft")
        v.tail.foldLeft[B](v.head)(op)
    }
}

private[mada] object ReduceRight {
    def apply[A, B >: A](v: Vector[A], op: (A, B) => B): B = {
        ThrowIf.empty(v, "reduceRight")
        v.reverse.reduceLeft(stl.Flip(op))
    }
}


private[mada] object ReducerLeft {
    def apply[A, B >: A](v: Vector[A], op: (B, A) => B): Vector[B] = {
        ThrowIf.empty(v, "reducerLeft")
        v.tail.folderLeft[B](v.head)(op)
    }
}

private[mada] object ReducerRight {
    def apply[A, B >: A](v: Vector[A], op: (A, B) => B): Vector[B] = {
        ThrowIf.empty(v, "reducerRight")
        v.reverse.reducerLeft(stl.Flip(op))
    }
}
