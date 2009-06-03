

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B]): Vector[A] = {
        ThrowIf.differentSize(v, w, "copyTo")
        stl.Copy(v, v.start, v.end, w, w.start)
        v
    }
}
