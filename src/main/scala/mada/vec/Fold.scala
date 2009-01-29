

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object FoldLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): B = {
        stl.Accumulate(v, v.start, v.end, z, op)
    }
}

private[mada] object FoldRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): B = {
        v.reverse.foldLeft(z)(Functions.flip(op))
    }
}


private[mada] object FolderLeft {
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

private[mada] object FolderRight {
    def apply[A, B](v: Vector[A], z: B, op: (A, B) => B): Vector[B] = {
        v.reverse.folderLeft(z)(Functions.flip(op))
    }
}
