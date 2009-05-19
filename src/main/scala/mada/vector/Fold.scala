

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object FolderLeft {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): Vector[B] = {
        val a = new Array[B](v.size + 1)
        var i = 0; var acc = z
        a(i) = acc
        for (e <- v) {
            i += 1; acc = op(acc, e)
            a(i) = acc
        }
        vector.fromArray(a).
            readOnly // consistent with parallels.
    }
}
