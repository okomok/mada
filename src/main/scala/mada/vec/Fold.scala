

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FoldLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): B = {
        val (x, i, j) = v.triple
        stl.Accumulate(x, i, j, z, op)
    }
}

object FoldRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): B = {
        v.reverse.foldLeft(z)(stl.Flip(op))
    }
}


object FolderLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): Vector[B] = {
        val a = new Array[B](v.size + 1)
        var i = 0; var acc = z
        a(i) = acc
        for (e <- v) {
            i += 1; acc = op(acc, e)
            a(i) = acc
        }
        Vector.arrayVector(a).
            readOnly // consistent with parallels.
    }
}

object FolderRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): Vector[B] = {
        v.reverse.folderLeft(z)(stl.Flip(op))
    }
}
